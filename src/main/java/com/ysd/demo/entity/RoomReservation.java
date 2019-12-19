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
@TableName("roomreservation")
public class RoomReservation {//客房预订表
	@JsonSerialize(using=ToStringSerializer.class)
	@TableId(value = "reservationId",type = IdType.AUTO)
	private Integer reservationId;//客房预订编号
	@TableField("rayamount")
	private Integer rayamount;//支付金额
	@TableField("reservaStartTime")
	private String reservaStartTime;//预订开始时间
	@TableField("reservaEndTime")
	private String reservaEndTime;//预订结束时间
	@TableField("reservaTime")
	private String reservaTime;//预订时间
	@TableField("reservaName")
	private String reservaName;//预订人姓名
	@TableField("reservaCard")
	private String reservaCard;//预订人身份证
	@TableField("reservaTel")
	private String reservaTel;//预订人电话
	@TableField("reservastate")
	private Integer reservastate;//入住状态（是否已入住）
	@TableField("roomId")
	private Integer roomId;//预留房间编号
	
	
	@TableField(exist = false)
	private Rooms rooms;
	
}
