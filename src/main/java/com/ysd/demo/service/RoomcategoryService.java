package com.ysd.demo.service;

import java.util.List;
import java.util.Map;

import com.ysd.demo.entity.Roomcategory;

public interface RoomcategoryService {
	
	/**
	 * 查询所有房间类别
	 * @return
	 */
	public List<Roomcategory> selectRoomcategoryAll();
	
	/**
	 * 分页查询所有房间类别
	 * @return
	 */
	public Map<String, Object> findAllRoomcategory(Integer page,Integer limit);
	
	/**
	 * 添加房间类别
	 * @param roomcategory
	 * @return
	 */
	public Integer insertRoomcategory(Roomcategory roomcategory);
	
	/**
	 * 修改房间类别
	 * @param roomcategory
	 * @return
	 */
	public Integer updateRoomcategory(Roomcategory roomcategory);
	
	/**
	 * 删除房间类别
	 * @param roomcategoryId
	 * @return
	 */
	public Integer deleteRoomcategory(Integer roomcategoryId);
	
}	
