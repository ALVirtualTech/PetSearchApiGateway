package ru.airlightvt.onlinerecognition.service;

import ru.airlightvt.onlinerecognition.entity.UploadFile;

import java.util.List;

/**
 * Сервис для сохрание данных
 * В зависимости от реализации файлы файлы могут храниться в бд, в файловой системе, в Amazon S3
 * @author apolyakov
 * @since 5.10.2018
 */
public interface IStorageService {
    /**
     * Сохранить набор данных
     * @param data данные
     * @param filename название файла
     */
    void store(byte[] data, String filename);

    /**
     * Загрузить файла по id
     * @param id файла
     * @return файл
     */
    UploadFile load(Long id);

    /**
     * Загрузить все сохраненные в системе файлы
     * @return список файлов
     */
    List<UploadFile> loadAll();

    /**
     * Удалить все сохраненные в системе файлы
     */
    void deleteAll();
}
