package io.favorites.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class LookRecordBiz extends Entitys implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private Long collectId;
    private Long createTime;
    private Long lastModifyTime;
}
