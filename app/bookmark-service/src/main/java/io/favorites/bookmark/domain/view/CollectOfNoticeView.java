package io.favorites.bookmark.domain.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import io.favorites.bookmark.domain.Notice;

@Projection(name = "noticeProjection", types = { Notice.class })
public interface CollectOfNoticeView{
    @Value("#{target.collect.id}")
	Long getId();

    @Value("#{target.user.id}")
	Long getUserId();

    @Value("#{target.user.profilePicture}")
	String getProfilePicture();

    @Value("#{target.collect.title}")
	String getTitle();

    @Value("#{target.collect.type}")
	String getType();

    @Value("#{target.collect.url}")
	String getUrl();

    @Value("#{target.collect.logoUrl}")
	String getLogoUrl();

    @Value("#{target.collect.remark}")
	String getRemark();

    @Value("#{target.collect.description}")
	String getDescription();

    @Value("#{target.collect.lastModifyTime}")
	Long getLastModifyTime();

    @Value("#{target.collect.createTime}")
	Long getCreateTime();

    @Value("#{target.user.userName}")
	String getUserName();

    @Value("#{target.collect.favorites.id}")
	Long getFavoritesId();

    @Value("#{target.collect.favorites.name}")
	String getFavoriteName();

    String getOperId();
}
