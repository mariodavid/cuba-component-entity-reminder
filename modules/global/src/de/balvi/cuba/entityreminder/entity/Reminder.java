package de.balvi.cuba.entityreminder.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@NamePattern("%s|caption")
@Table(name = "DBCER_REMINDER")
@Entity(name = "dbcer$Reminder")
public class Reminder extends StandardEntity {
    private static final long serialVersionUID = -4026375619004855173L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Future
    @Column(name = "REMIND_AT")
    protected Date remindAt;

    @Column(name = "DONE")
    protected Boolean done;

    @NotNull
    @Column(name = "CAPTION", nullable = false)
    protected String caption;

    @Column(name = "ENTITY_ID", nullable = false)
    protected UUID entityId;

    @NotNull
    @Column(name = "ENTITY_CLASS", nullable = false)
    protected String entityClass;

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getDone() {
        return done;
    }


    public void setRemindAt(Date remindAt) {
        this.remindAt = remindAt;
    }

    public Date getRemindAt() {
        return remindAt;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getEntityClass() {
        return entityClass;
    }

}