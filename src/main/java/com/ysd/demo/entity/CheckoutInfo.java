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
public class CheckoutInfo {//客户结账退房信息表
	@JsonSerialize(using=ToStringSerializer.class)
	@TableId(value = "checkoutInfoId",type = IdType.AUTO)
	private Integer checkoutInfoId;//退房编号
	@TableField("roomsId")
	private Integer roomsId;//退房房间号
	@TableField("checkoutPrice")
	private Integer checkoutPrice;//价格
	@TableField("checkouTime")
	private String checkouTime;//退房时间
}
