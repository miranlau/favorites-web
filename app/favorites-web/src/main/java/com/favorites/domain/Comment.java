package com.favorites.domain;

import com.favorites.remote.ServiceRegistry;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class Comment extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long collectId;
	@Column(nullable = false, length = 65535, columnDefinition = "Text")
	private String content;
	@Column(nullable = false)
	private Long userId;
	@Column(nullable = true)
	private Long replyUserId;
	@Column(nullable = false)
	private Long createTime;
	@Transient
	private String commentTime;
	@Transient
	private String userName;
	@Transient
	private String replyUserName;
	@Transient
	private String profilePicture;
	@Transient
	private String user;
	@Transient
	private String collect;

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
		if(collectId != null){
			collect = "http://" + ServiceRegistry.getCommentServiceAddr() + "/collects/" + collectId;
//			user = "http://localhost:8096/collects/" + collectId;
		}
	}

	public void setUserId(Long userId) {
		this.userId = userId;
		if (userId != null) {
			user = "http://" + ServiceRegistry.getCommentServiceAddr() + "/users/" + userId;
//			user = "http://localhost:8096/users/" + userId;
		}
	}
}