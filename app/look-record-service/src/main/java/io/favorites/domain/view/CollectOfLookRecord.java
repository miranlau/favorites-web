package io.favorites.domain.view;

import io.favorites.domain.LookRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "lookRecordProjection", types = { LookRecord.class })
public interface CollectOfLookRecord {
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