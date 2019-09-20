package io.favorites.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  浏览记录entity
 */
@Entity
@Data
public class LookRecord implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long lastModifyTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "collect_id", nullable = true, updatable = false)
    private Collect collect;

}
