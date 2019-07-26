package ru.airlightvt.onlinerecognition.entity;

import ru.airlightvt.onlinerecognition.auth.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "ADVERTS")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "fk_photo_id", referencedColumnName = "id")
    private UploadFile photo;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Advert() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UploadFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadFile photo) {
        this.photo = photo;
    }
}
