package io.favorites.bookmark.domain.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import io.favorites.bookmark.domain.Collect;

@Projection(name = "collectProjection", types = { Collect.class })
public interface CollectView{
	Long getId();

    @Value("#{target.user.id}")
	Long getUserId();

    @Value("#{target.user.profilePicture}")
	String getProfilePicture();
	String getTitle();
	String getType();
	String getUrl();
	String getLogoUrl();
	String getRemark();
	String getDescription();
	Long getLastModifyTime();
	Long getCreateTime();

    @Value("#{target.user.userName}")
	String getUserName();

    @Value("#{target.favorites.id}")
	Long getFavoritesId();

    @Value("#{target.favorites.name}")
	String getFavoriteName();
}
