package ru.airlightvt.onlinerecognition.common.data.entity;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * Абстрактная сущность.
 * Должна использоваться в качестве основы для всех остальных сущностей
 *
 * @author apolyakov
 * @since 21.07.2019
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity  implements Persistable<Long>, Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long id() {
        Assert.notNull(id, "Entity must has id");
        return id;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

    @Override
    public Long getId() {
        return id();
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
