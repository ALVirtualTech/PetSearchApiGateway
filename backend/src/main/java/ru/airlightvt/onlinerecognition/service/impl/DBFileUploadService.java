package ru.airlightvt.onlinerecognition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.entity.UploadFile;
import ru.airlightvt.onlinerecognition.repository.UploadFileRepository;
import ru.airlightvt.onlinerecognition.service.IStorageService;

import java.util.List;

/**
 * Сервис для работы с загруженными файлами изображений
 * @author apolyakov
 * @since 5.10.2018
 */
@Service
@Transactional
public class DBFileUploadService implements IStorageService {
    private UploadFileRepository fileUploadRepository;

    @Autowired
    public DBFileUploadService(UploadFileRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    public void store(byte[] data, String filename)
    {
        UploadFile file = new UploadFile(filename, data);
        fileUploadRepository.save(file);
    }

    public UploadFile load(Long id)
    {
        return fileUploadRepository.getById(id);
    }

    public List<UploadFile> loadAll()
    {
        return fileUploadRepository.findAll();
    }

    public void deleteAll()
    {
        fileUploadRepository.deleteAll();
    }
}
