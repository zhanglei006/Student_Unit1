package com.ysd.demo.entity;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
@Component
@TableName("roomRuZhu")
public class RoomRuZhu {
	@JsonSerialize(using=ToStringSerializer.class)
	@TableId(value = "roomRuZhuId",type = IdType.AUTO)
	private Integer roomRuZhuId;//入住编号
	@TableField("roomId")
	private Integer roomId;//房间号
	@TableField("rayamount")
	private Integer rayamount;//支付金额
	@TableField("startTime")
	private String startTime;//开始时间
	@TableField("endTime")
	private String endTime;//结束时间
	@TableField("ruZhuName")
	private String ruZhuName;//开房人姓名
	@TableField("ruZhuCard")
	private String ruZhuCard;//开放人身份证
	@TableField("peoplenum")
	private Integer peoplenum;//入住人数
	@TableField("ruzhustate")
	private Integer ruzhustate;//入住状态（已入住/已退房）
	
	@TableField(exist = false)
	private Rooms rooms;
	
}
