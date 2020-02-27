package ru.airlightvt.onlinerecognition.security.entity;

import ru.airlightvt.onlinerecognition.common.data.entity.AbstractBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class UploadFile  extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = -5606013423664346433L;

    private String fileName;
    @Lob
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "photo")
    private Advert advert;

    public UploadFile() {
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public UploadFile(String filename, byte[] data) {
        this.fileName = filename;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
