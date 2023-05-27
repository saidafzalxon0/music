package uz.java.music.service.mapper;

public interface CommonMapper<D,E> {

    D toDto(E e);

    E toEntity(D d);
}
