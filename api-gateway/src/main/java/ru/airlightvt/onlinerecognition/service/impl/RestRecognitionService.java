package ru.airlightvt.onlinerecognition.service.impl;

import ru.airlightvt.onlinerecognition.service.IRestRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.airlightvt.onlinerecognition.service.IConsulService;
import ru.airlightvt.onlinerecognition.common.data.entity.Constants;

import javax.naming.ServiceUnavailableException;
import java.net.URI;

/**
 * Сервис для взаимодействия с микросервисом распознавания изображений животных
 *
 * @author apolyakov
 * @since 27.07.2019
 */
@Service
public class RestRecognitionService implements IRestRecognitionService {
    private final IConsulService consulService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public RestRecognitionService(IConsulService consulService)
    {
        this.consulService = consulService;
    }

    /**
     * Найти объявления, содержащие изображения похожие на данное
     * (TODO: на данный момент лишь заглушка, после реализации микросервиса, доработать)
     * @param base64Image изображение для анализа
     * @return JSON с найденными объявлениями
     * @throws ServiceUnavailableException микросервис не доступен
     */
    public String findSamePetsByPhoto(String base64Image) throws ServiceUnavailableException
    {
        URI service = consulService.serviceUrl(Constants.ServicesNames.PET_RECOGNITION_SERVICE)
                .map(s -> s.resolve("/ping"))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate.getForEntity(service, String.class)
                .getBody();
    }

}
