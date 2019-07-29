package ru.airlightvt.onlinerecognition.shared.dto;

import ru.airlightvt.onlinerecognition.entity.Advert;

import java.io.Serializable;

/**
 * POJO для сериализации и передачи между сервисами
 *
 * TODO: перевести DTO как наследников DtObject -> мапа пропертей - интерфейс, который можно будет использовать во всех проектах
 */
public class AdvertDto implements Serializable {
    private long id;
    private String title;
    private String description;
    private String image;
    private String breed;
    private boolean vaccinations;
    private float height;
    private float weight;
    private String coatColor;

    public AdvertDto(long id, String title, String description, String image, String breed, boolean vaccinations, float height, float weight, String coatColor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.breed = breed;
        this.vaccinations = vaccinations;
        this.height = height;
        this.weight = weight;
        this.coatColor = coatColor;
    }

    public AdvertDto(Advert source) {
        this.id = source.getId();
        this.title = source.getTitle();
        this.description = source.getDescription();
        this.image = source.getImage();
        this.breed = source.getBreed();
        this.vaccinations = source.isVaccinations();
        this.height = source.getHeight();
        this.weight = source.getWeight();
        this.coatColor = source.getCoatColor();
    }

    public AdvertDto() {
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

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
