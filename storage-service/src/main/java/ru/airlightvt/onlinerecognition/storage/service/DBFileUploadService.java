package ru.airlightvt.onlinerecognition.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.airlightvt.onlinerecognition.storage.data.StorageSchema;
import ru.airlightvt.onlinerecognition.storage.data.UploadFile;
import ru.airlightvt.onlinerecognition.storage.repository.UploadFileRepository;

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
        StorageSchema schema = StorageSchema.DB;
        // todo: For DB storage uri format is "db://username/filename"
        String uri = String.format("%s://%s", schema, filename);
        UploadFile file = new UploadFile(filename, data, schema, uri);
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
