package ru.airlightvt.onlinerecognition.common.data.entity;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Абстрактная сущность, имеющая название
 *
 * @author apolyakov
 * @since 21.07.2019
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractNamedEntity extends AbstractBaseEntity {
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    @SafeHtml(groups = {View.Web.class})
    protected String name;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, name);
    }
}
