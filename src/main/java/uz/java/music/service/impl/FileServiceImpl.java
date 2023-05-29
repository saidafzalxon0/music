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
import uz.java.music.dto.FileDto;
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
    public ResponseEntity<FileDto> add(MultipartFile multipartFile) {
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
        return new ResponseEntity<>(mapper.toDto(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FileDto>> search(String ext) {
        try {
            return new ResponseEntity<>(repository.findAllByExt(ext).stream().map(mapper::toDto).toList(), HttpStatus.OK);
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new NotSaved("Database not connected");
        }
    }


    @Override
    public ResponseEntity<FileDto> delete(Long id) {
        Optional<uz.java.music.entity.File> optional = repository.findById(id);
        if(optional.isPresent()){
            try{
                amazon.deleteObject(bucket_name,optional.get().getLink().substring(optional.get().getLink().lastIndexOf("/")+1));
                repository.deleteFile(id);
                return new ResponseEntity<>(mapper.toDto(optional.get()),HttpStatus.OK);
            }catch (Exception e){
                throw new NotSaved(e.getMessage());
            }
        }else{
            throw new NotFound("File is not found");
        }
    }

    @Override
    public ResponseEntity<List<FileDto>> getAll() {
        try {
            return new ResponseEntity<>(repository.findAll().stream().map(mapper::toDto).toList(), HttpStatus.OK);
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
