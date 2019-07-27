package ru.airlightvt.onlinerecognition.shared.trasformer;

import ru.airlightvt.onlinerecognition.entity.Advert;
import ru.airlightvt.onlinerecognition.shared.dto.AdvertDto;

/**
 * Преобразование из сущности в DTO и обратно для объявлений
 */
public class AdvertConverter {
    public static Advert convert(AdvertDto source)
    {
        return source == null ? null : new Advert(source);
    }

    public static AdvertDto convert(Advert source)
    {
        return source == null ? null : new AdvertDto(source);
    }
}
