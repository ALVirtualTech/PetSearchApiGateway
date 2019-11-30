package ru.airlightvt.onlinerecognition.repository;

import org.springframework.data.repository.CrudRepository;
import ru.airlightvt.onlinerecognition.entity.UploadFile;

import java.util.List;

public interface UploadFileRepository  extends CrudRepository<UploadFile, Long> {
    UploadFile getById(Long id);
    List<UploadFile> findAll();
}
