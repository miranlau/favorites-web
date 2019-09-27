package com.favorites.domain.view;

public interface CommentView {
	Long getId();
	Long getUserId();
	String getUserName();
	String getProfilePicture();
	String getContent();
	Long getCreateTime();
	Long getReplyUserId();
}
