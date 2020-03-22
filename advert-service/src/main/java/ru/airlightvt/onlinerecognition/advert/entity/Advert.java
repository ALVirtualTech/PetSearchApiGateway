package ru.airlightvt.onlinerecognition.advert.entity;

import com.google.common.collect.Lists;
import io.eventuate.tram.events.ResultWithEvents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.airlightvt.onlinerecognition.common.data.entity.AbstractNamedEntity;
import ru.airlightvt.onlinerecognition.common.data.dto.AdvertDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@Access(AccessType.FIELD)
@NoArgsConstructor
public class Advert extends AbstractNamedEntity implements Serializable {
    private static final long serialVersionUID = 7917317924514100855L;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Long author;
    private Long photoId;

    private AdvertState state;

    //@OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    @ElementCollection
    @CollectionTable(name = "advert_images")
    private List<AdvertImage> images;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    private List<TagWithValue> tagWithValues;

    private String breed;
    private boolean vaccinations;
    private float height;
    private float weight;
    private String coatColor;

    public Advert(AdvertDto source) {
        super(source.getId(), source.getTitle());
        this.description = source.getDescription();
        this.author = null;
        this.photoId = source.getImageId();
        this.breed = source.getBreed();
        this.vaccinations = source.isVaccinations();
        this.height = source.getHeight();
        this.weight = source.getWeight();
        this.coatColor = source.getCoatColor();
    }

    public static ResultWithEvents<Advert> createAdvert(AdvertDto advertDto) {
        Advert advert = new Advert(advertDto);
        // TODO: there should be list of domain events
        return new ResultWithEvents<>(advert, Lists.newArrayList());
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }
}
