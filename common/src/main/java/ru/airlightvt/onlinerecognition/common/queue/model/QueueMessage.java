package ru.airlightvt.onlinerecognition.common.queue.model;

import java.io.Serializable;
import java.util.Arrays;

public class QueueMessage implements Serializable {
    private static final long serialVersionUID = -7860755788485783784L;

    private long id;
    private byte[] image;
    private long articleId;

    public QueueMessage() {
    }

    public QueueMessage(byte[] image, long articleId) {
        this.image = image;
        this.articleId = articleId;
    }

    public QueueMessage(long id, byte[] image, long articleId) {
        this.id = id;
        this.image = image;
        this.articleId = articleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "QueueMessage{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", articleId=" + articleId +
                '}';
    }
}
