package io.favorites.bookmark.domain;

import java.io.Serializable;

import javax.persistence.*;

import io.favorites.bookmark.domain.enums.CollectType;
import io.favorites.bookmark.domain.enums.IsDelete;

@Entity
@Table(name = "collect")
public class Collect2  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "favorites_id", nullable = false)
    private Long favoritesId;
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

	public Collect2() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public CollectType getType() {
		return type;
	}

	public void setType(CollectType type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public IsDelete getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(IsDelete isDelete) {
		this.isDelete = isDelete;
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

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public String getNewFavorites() {
		return newFavorites;
	}

	public void setNewFavorites(String newFavorites) {
		this.newFavorites = newFavorites;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    /**
     * @return Returns the userId.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId The userId to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return Returns the favoritesId.
     */
    public Long getFavoritesId() {
        return favoritesId;
    }

    /**
     * @param favoritesId The favoritesId to set.
     */
    public void setFavoritesId(Long favoritesId) {
        this.favoritesId = favoritesId;
    }
}
