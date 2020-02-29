package ru.airlightvt.onlinerecognition.advert.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.airlightvt.onlinerecognition.common.data.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@NoArgsConstructor
public class TagWithValue extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = -6419098865648751031L;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private Advert advert;

    private String value;
}
