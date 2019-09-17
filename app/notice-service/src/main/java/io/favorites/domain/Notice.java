package io.favorites.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 消息
 *
 * 
 */
@Entity
@Data
public class Notice extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String type;
//	@Column(nullable = true)
//	private String operId;//It is comment id.
	@Column(nullable = false)
	private String readed;
	@Column(nullable = false)
	private Long createTime;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "collect_id", nullable = false, updatable = false)
	private Collect collect;

	@OneToOne
	@JoinColumn(name = "oper_id", nullable = false, updatable = false)
	private Comment comment;


}