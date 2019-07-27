package ru.airlightvt.onlinerecognition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import ru.airlightvt.onlinerecognition.service.IConsulService;

import java.net.URI;
import java.util.Optional;

/**
 * Сервис для работы с механизмом обнаружения сервисов на основе Consul.
 *
 * @author apolyakov
 * @since 27.07.2019
 */
@Service
public class ConsulService implements IConsulService {
    private final DiscoveryClient discoveryClient;

    @Autowired
    public ConsulService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /**
     * Находит первый из доступных инстансов сервиса по его имени
     * @param serviceName имя сервиса
     * @return URI сервиса для доступа к нему по http
     */
    public Optional<URI> serviceUrl(String serviceName) {
        return discoveryClient.getInstances(serviceName)
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }
}
