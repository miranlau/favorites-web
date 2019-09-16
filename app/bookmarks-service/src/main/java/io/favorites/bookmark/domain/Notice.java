package io.favorites.bookmark.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = true)
    private String operId;
    @Column(nullable = false)
    private String readed;
    @Column(nullable = false)
    private Long createTime;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "collect_id", nullable = true, updatable = false)
    private Collect collect;

    public Notice() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getReaded() {
        return readed;
    }

    public void setReaded(String readed) {
        this.readed = readed;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Returns the collect.
     */
    public Collect getCollect() {
        return collect;
    }

    /**
     * @param collect The collect to set.
     */
    public void setCollect(Collect collect) {
        this.collect = collect;
    }

}
