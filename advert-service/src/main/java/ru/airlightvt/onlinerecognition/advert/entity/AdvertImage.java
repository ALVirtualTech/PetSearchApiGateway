package ru.airlightvt.onlinerecognition.advert.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.airlightvt.onlinerecognition.common.data.entity.AbstractBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Access(AccessType.FIELD)
@Table
@Data
@NoArgsConstructor
public class AdvertImage extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = -5640303815203134748L;

    private Long imageId;

    @ManyToOne
    private Advert advert;
}
