package io.favorites.comment.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "commentProjection", types = { Comment.class })
public interface CommentView {
	Long getId();
	@Value("#{target.user.id}")
	Long getUserId();
	@Value("#{target.user.userName}")
	String getUserName();
	@Value("#{target.user.profilePicture}")
	String getProfilePicture();
	String getContent();
	Long getCreateTime();
	Long getReplyUserId();
}
