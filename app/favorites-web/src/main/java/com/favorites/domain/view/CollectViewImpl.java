/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.domain.view;

/**
 * This class is a POJO implements CollectView to encapsulates json
 * objects.
 */
public class CollectViewImpl implements CollectView {

    private Long id;
    private Long userId;
    private String profilePicture;
    private String title;
    private String type;
    private String url;
    private String logoUrl;
    private String remark;
    private String description;
    private Long lastModifyTime;
    private Long createTime;
    private String userName;
    private Long favoritesId;
    private String favoriteName;
    private String OperId;

    /**
     * Creates a new instance of <code>CollectViewImpl</code>.
     */
    public CollectViewImpl() {
    }

    /**
     * @return Returns the id.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the userId.
     */
    @Override
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
     * @return Returns the profilePicture.
     */
    @Override
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture The profilePicture to set.
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return Returns the title.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the type.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the url.
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return Returns the logoUrl.
     */
    @Override
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * @param logoUrl The logoUrl to set.
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * @return Returns the remark.
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return Returns the description.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the lastModifyTime.
     */
    @Override
    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * @param lastModifyTime The lastModifyTime to set.
     */
    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * @return Returns the createTime.
     */
    @Override
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime The createTime to set.
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the userName.
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return Returns the favoritesId.
     */
    @Override
    public Long getFavoritesId() {
        return favoritesId;
    }

    /**
     * @param favoritesId The favoritesId to set.
     */
    public void setFavoritesId(Long favoritesId) {
        this.favoritesId = favoritesId;
    }

    /**
     * @return Returns the favoriteName.
     */
    @Override
    public String getFavoriteName() {
        return favoriteName;
    }

    /**
     * @param favoriteName The favoriteName to set.
     */
    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    /**
     * @return Returns the operId.
     */
    @Override
    public String getOperId() {
        return OperId;
    }

    /**
     * @param operId The operId to set.
     */
    public void setOperId(String operId) {
        OperId = operId;
    }
}
