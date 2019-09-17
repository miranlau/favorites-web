package io.favorites.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 点赞
 *
 * 
 */
@Entity
@Data
public class Praise extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long collectId;
	@Column(nullable = false)
	private Long userId;
	@Column(nullable = false)
	private Long createTime;

	public Praise() {
		super();
	}

}