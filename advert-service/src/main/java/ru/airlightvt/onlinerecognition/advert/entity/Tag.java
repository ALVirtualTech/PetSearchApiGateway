package ru.airlightvt.onlinerecognition.advert.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.airlightvt.onlinerecognition.common.data.entity.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Access(AccessType.FIELD)
@Table
@Data
@NoArgsConstructor
public class Tag extends AbstractNamedEntity implements Serializable {
    private static final long serialVersionUID = -3166434186270180499L;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Advert> adverts;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<TagWithValue> tagWithValues;
}
