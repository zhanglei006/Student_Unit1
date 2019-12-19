package com.ysd.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysd.demo.entity.RoomRuZhu;

public interface RoomRuZhuMapper extends BaseMapper<RoomRuZhu>{
	
	IPage<RoomRuZhu> customizeSelectByRoomRuZhu(Page<RoomRuZhu> page,@Param(Constants.WRAPPER) Wrapper<RoomRuZhu> wrapper);
}
