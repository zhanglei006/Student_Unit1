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
public class Rooms {//酒店房间信息表
	@JsonSerialize(using=ToStringSerializer.class)
	@TableId(value = "roomId",type = IdType.AUTO)
	private Integer roomId;//房间id
	@TableField("roomSize")
	private Integer roomSize;//房间面积
	@TableField("roomFloors")
	private Integer roomFloors;//房间楼层
	@TableField("Iswindow")
	private Integer Iswindow;//是否有窗户
	@TableField("IsWifi")
	private Integer IsWifi;//是否有wifi
	@TableField("IsBroadband")
	private Integer IsBroadband;//是否有宽带
	@TableField("afewhuman")
	private Integer afewhuman;//几人间
	@TableField("roomprice")
	private Integer roomprice;//房间价格
	@TableField("roomInstructions")
	private String roomInstructions;//房间说明
	@TableField("preferential")
	private String preferential;//优惠活动
	@TableField("IsFree")
	private Integer IsFree;//是否空闲
	@TableField("roomcategoryId")
	private Integer roomcategoryId;//房间类别ID
	@TableField("roommun")
	private Integer roommun;//房间号
	
	
	@TableField(exist = false)
	private Roomcategory roomcategory;
	@TableField(exist = false)
	private  Pictures pictures;
	
	
}
