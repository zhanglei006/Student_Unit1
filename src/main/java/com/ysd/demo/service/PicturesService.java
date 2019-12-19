package com.ysd.demo.service;

import java.util.Map;

import com.ysd.demo.entity.Pictures;

public interface PicturesService {
	
	/**
	 * 分页查询所有图片信息
	 * @param page
	 * @param limit
	 * @return
	 */
	Map<String, Object> findAllPictures(Integer page,Integer limit);
	/**
	 * 添加图片信息
	 * @param pictures
	 * @return
	 */
	Integer insertPictures(Pictures pictures);
	
	/**
	 * 修改图片信息
	 * @param pictures
	 * @return
	 */
	Integer updatePictures(Pictures pictures);
	/**
	 * 修改是否主图图片信息
	 * @param pictures
	 * @return
	 */
	Integer updatePicture(Pictures pictures,Integer roommun);
	
	/**
	 * 删除图片信息
	 * @param picturesId
	 * @return
	 */
	Integer deletePictures(Integer picturesId,Integer roomId);
	
	/**
	 * 确认替换主图
	 * @return
	 */
	Integer querentihuan(Pictures pictures);
	
	/**
	 * 取消替换主图
	 * @return
	 */
	Integer quxiaotihuan(Pictures pictures);
	
}
