package ru.airlightvt.onlinerecognition.api_gateway.service;

import javax.naming.ServiceUnavailableException;

/**
 * Сервис для взаимодействия с микросервисом распознавания изображений животных
 *
 * @author apolyakov
 * @since 27.07.2019
 */
public interface IRestRecognitionService {
    /**
     * Найти объявления, содержащие изображения похожие на данное
     * @param base64Image изображение для анализа
     * @return JSON с найденными объявлениями
     * @throws ServiceUnavailableException микросервис не доступен
     */
    String findSamePetsByPhoto(String base64Image) throws ServiceUnavailableException;
}
