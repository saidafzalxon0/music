package uz.java.music.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.java.music.dto.EventDto;
import uz.java.music.dto.FileDto;
import uz.java.music.dto.ResponseDto;
import uz.java.music.entity.Aspirant;
import uz.java.music.exception.NotFound;
import uz.java.music.exception.NotSaved;
import uz.java.music.repository.FileRepository;
import uz.java.music.service.FileService;
import uz.java.music.service.mapper.FileMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {
    @Value("${bucketName}")
    private String bucket_name;
    @Autowired
    private AmazonS3 amazon;

    @Autowired
    private FileRepository repository;

    @Autowired
    private FileMapper mapper;
    @Override
    @Transactional
    public ResponseDto<FileDto> add(MultipartFile multipartFile) {
        String name = String.valueOf(UUID.randomUUID());
        int ext = multipartFile.getOriginalFilename().lastIndexOf(".");
        name += multipartFile.getOriginalFilename().substring(ext);
        uz.java.music.entity.File entity = new uz.java.music.entity.File();
        File file = null;
        try {
            file = convertMultipartToFile(multipartFile,name);
            amazon.putObject(bucket_name,name,file);
            file.delete();
            entity = uz.java.music.entity.File.builder().link("https://" + amazon.getUrl(bucket_name,name).getHost()+amazon.getUrl(bucket_name,name).getFile()).ext(multipartFile.getOriginalFilename().substring(ext+1)).build();
            entity = repository.save(entity);
        } catch (IOException e) {
           new NotSaved(e.getMessage());
        }
        return ResponseDto.<FileDto>builder().data(mapper.toDto(entity)).status("success").build();
    }

    @Override
    public ResponseDto<List<FileDto>> search(String ext) {
        try {
            return ResponseDto.<List<FileDto>>builder().data(repository.findAllByExt(ext).stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }


    @Override
    @Transactional
    public ResponseDto<FileDto> delete(Long id) {
        Optional<uz.java.music.entity.File> optional = repository.findById(id);
        if (optional.isPresent()) {
            amazon.deleteObject(bucket_name, optional.get().getLink().substring(optional.get().getLink().lastIndexOf("/") + 1));
            repository.deleteFile(id);
            return ResponseDto.<FileDto>builder().data(mapper.toDto(optional.get())).status("success").build();

        }else{
            throw new NotFound("File not found");
        }
    }

    @Override
    public ResponseDto<List<FileDto>> getAll() {
        try {
            return ResponseDto.<List<FileDto>>builder().data(repository.findAll().stream().map(mapper::toDto).toList()).status("success").build();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }


    private File convertMultipartToFile(MultipartFile multipartFile,String name) throws IOException {
        File file = new File(name);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}
