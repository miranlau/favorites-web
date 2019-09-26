package io.favorites.comment.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "comment")
public class Comment2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "collect_id", nullable = false, updatable = false)
	private Collect collect;

	@Column(nullable = false, length = 65535, columnDefinition = "Text")
	private String content;

	@Column(nullable = false)
	private Long createTime;

	@Transient
	private String commentTime;

	@Column
	private Long replyUserId;

	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

//	@Transient
//	private String userName;
//
//	@Transient
//	private String replyUserName;
//
//	@Transient
//	private String profilePicture; // in User

	public Comment2() {
		super();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}