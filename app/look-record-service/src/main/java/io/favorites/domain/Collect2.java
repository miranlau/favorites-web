package io.favorites.domain;

import io.favorites.domain.enums.CollectType;
import io.favorites.domain.enums.IsDelete;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "collect")
@Data
public class Collect2  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, columnDefinition = "varchar(600)")
	private String url;
	@Column(nullable = false)
	private String title;
	@Column(nullable = true, length = 65535, columnDefinition = "Text")
	private String description;
	@Column(nullable = true,columnDefinition = "varchar(300)")
	private String logoUrl;
	@Column(nullable = true)
	private String charset;
    @Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private CollectType type;
	@Column(nullable = true)
	private String remark;
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private IsDelete isDelete;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Long lastModifyTime;
	@Column(nullable = true)
	private String category;
	@Transient
	private String collectTime;
	@Transient
	private String newFavorites;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "favorites_id", nullable = false, updatable = false)
    private Favorites favorites;

}
