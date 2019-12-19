package com.ysd.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysd.demo.entity.Pictures;

public interface PicturesMapper extends BaseMapper<Pictures>{
	
	IPage<Pictures> customizeSelectBypictures(Page<Pictures> page,@Param(Constants.WRAPPER) Wrapper<Pictures> wrapper);
}
