package com.ysd.demo.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
@Component
public class Roomcategory {//酒店客房类别表
	@JsonSerialize(using=ToStringSerializer.class)
	@TableId(value = "roomcategoryId",type = IdType.AUTO)
	private Integer roomcategoryId;//客房类别id
	@TableField("roomcategoryName")
	private String roomcategoryName;//客房类别名称

}