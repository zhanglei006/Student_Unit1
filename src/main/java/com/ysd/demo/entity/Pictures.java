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
public class Pictures {//图片表
	@JsonSerialize(using=ToStringSerializer.class)
	@TableId(value = "picturesId",type = IdType.AUTO)
	private Integer picturesId;//图片编号
	@TableField("picturesUrl")
	private String picturesUrl;//图片路径
	@TableField("roomId")
	private Integer roomId;
	@TableField("hotelIdPictureid")
	private Integer hotelIdPictureid;
	@TableField("iszhutu")
	private Integer iszhutu;
	
	
	@TableField(exist = false)
	private Rooms rooms;
}
