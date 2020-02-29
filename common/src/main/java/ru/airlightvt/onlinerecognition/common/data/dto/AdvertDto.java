package ru.airlightvt.onlinerecognition.common.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * POJO для сериализации и передачи между сервисами
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto implements Serializable {
    private static final long serialVersionUID = 5026020201803022558L;

    private long id;
    private String title;
    private String description;
    private Long imageId;
    private String breed;
    private boolean vaccinations;
    private float height;
    private float weight;
    private String coatColor;
}
