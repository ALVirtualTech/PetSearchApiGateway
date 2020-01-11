package ru.airlightvt.onlinerecognition.entity;

import ru.airlightvt.onlinerecognition.data.entity.AbstractNamedEntity;
import ru.airlightvt.onlinerecognition.shared.dto.AdvertDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADVERTS")
public class Advert extends AbstractNamedEntity implements Serializable {
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private User author;

    @Deprecated
    @OneToOne
    @JoinColumn(name = "fk_photo_id", referencedColumnName = "id", nullable = true)
    private UploadFile photo;

    @Column(columnDefinition = "TEXT")
    @Lob
    private String image;
    private String breed;
    private boolean vaccinations;
    private float height;
    private float weight;
    private String coatColor;

    public Advert() {
    }

    public Advert(Advert advert) {
        this(
                advert.getId(),
                advert.getName(),
                advert.getDescription(),
                advert.getAuthor(),
                advert.getImage(),
                advert.getBreed(),
                advert.isVaccinations(),
                advert.getHeight(),
                advert.getWeight(),
                advert.getCoatColor()
        );
    }

    public Advert(Long id, String title, String description, User author, String image, String breed, boolean vaccinations, float height, float weight, String coatColor) {
        super(id, title);
        this.description = description;
        this.author = author;
        this.image = image;
        this.breed = breed;
        this.vaccinations = vaccinations;
        this.height = height;
        this.weight = weight;
        this.coatColor = coatColor;
    }

    public Advert(AdvertDto source) {
        this(
                source.getId(),
                source.getTitle(),
                source.getDescription(),
                null,
                source.getImage(),
                source.getBreed(),
                source.isVaccinations(),
                source.getHeight(),
                source.getWeight(),
                source.getCoatColor()
        );
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(boolean vaccinations) {
        this.vaccinations = vaccinations;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadFile photo) {
        this.photo = photo;
    }
}
