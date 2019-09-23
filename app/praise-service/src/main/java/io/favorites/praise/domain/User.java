package io.favorites.praise.domain;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {

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
	
	@Column(nullable = true)
	private String outDate;
	
	@Column(nullable = true)
	private String validataCode;
	
	@Column(nullable = true)
	private String backgroundPicture;
	
	@Column(nullable = false)
	private Long createTime;
	
	@Column(nullable = false)
	private Long lastModifyTime;

	public User() {
		super();
	}
	public User(String email, String passWord, String userName) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
