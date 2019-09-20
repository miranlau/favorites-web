package com.favorites.domain;

import com.favorites.remote.ServiceRegistry;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  浏览记录entity
 * Created by chenzhimin on 2017/1/5.
 */
@Entity
public class LookRecord extends Entitys implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long collectId;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long lastModifyTime;
    @Transient
    private String user;
    @Transient
    private String collect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
        if (userId != null) {
            user = ServiceRegistry.getLookRecordServiceAddr() + "/users/" + userId;
        }
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
        if (collectId != null) {
            collect = ServiceRegistry.getLookRecordServiceAddr() + "/collects/" + collectId;
        }
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }
}
