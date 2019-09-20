package io.favorites.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户
 * @author DingYS
 *
 */
@Entity
@Data
public class User extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String passWord;
	@Column(nullable = false, unique = true)
	private String email;	
	@Column(nullable = true)
	private String profilePicture;
	@Column(nullable = true,length = 65535,columnDefinition="Text")
	private String introduction;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Long lastModifyTime;
	@Column(nullable = true)
	private String outDate;
	@Column(nullable = true)
	private String validataCode;
	@Column(nullable = true)
	private String backgroundPicture;

	public User() {
		super();
	}
	public User(String email, String nickName, String passWord, String userName) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.userName = userName;
	}
}