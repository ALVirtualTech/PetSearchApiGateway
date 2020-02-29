package ru.airlightvt.onlinerecognition.storage.data;

import lombok.*;
import ru.airlightvt.onlinerecognition.common.data.entity.AbstractBaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile  extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = -5606013423664346433L;

    private String fileName;
    @Lob
    private byte[] data; // only for storing file into DB

    private StorageSchema schema;

    private String url;
}
