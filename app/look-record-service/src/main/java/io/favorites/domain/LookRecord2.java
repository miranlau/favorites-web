package io.favorites.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  浏览记录entity
 */
@Entity
@Table(name = "look_record")
@Data
public class LookRecord2 implements Serializable{

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "bigint(20)")
    private Long createTime;
    @Column(nullable = false, columnDefinition = "bigint(20)")
    private Long lastModifyTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "collect_id", nullable = false)
    private Collect collect;

}
