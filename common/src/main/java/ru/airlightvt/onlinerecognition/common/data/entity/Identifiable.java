package ru.airlightvt.onlinerecognition.common.data.entity;

import java.io.Serializable;

/**
 * Interface to mark objects that are identifiable by an ID of any type.
 */
public interface Identifiable<ID extends Serializable> {
    /**
     * Returns the id identifying the object.
     *
     * @return the identifier or {@literal null} if not available.
     */
    ID getId();

    void setId(ID id);

    default boolean isNew() {
        return getId() == null;
    }
}
