package ru.airlightvt.onlinerecognition.advert.trasformer;

import ru.airlightvt.onlinerecognition.common.data.dto.AdvertDto;
import ru.airlightvt.onlinerecognition.advert.entity.Advert;

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
        return source == null ? null : AdvertDto.builder()
                .id(source.id())
                .title(source.getTitle())
                .breed(source.getBreed())
                .coatColor(source.getCoatColor())
                .description(source.getDescription())
                .height(source.getHeight())
                .weight(source.getWeight())
                .vaccinations(source.isVaccinations())
                .imageId(source.getPhotoId())
                .build();
    }
}
