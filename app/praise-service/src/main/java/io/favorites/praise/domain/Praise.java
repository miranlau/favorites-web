package io.favorites.praise.domain;

import lombok.Data;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@Entity
@Data
public class Praise implements Serializable {

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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}