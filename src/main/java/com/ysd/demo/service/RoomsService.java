package com.ysd.demo.service;

import java.util.List;
import java.util.Map;

import com.ysd.demo.entity.Rooms;

public interface RoomsService {
	/**
	 * 分页多条件查询酒店房间信息
	 * @param page
	 * @param limit
	 * @param rooms
	 * @return
	 */
	Map<String, Object> selectRoomsAll(Integer page,Integer limit,Rooms rooms);
	/**
	 * 添加酒店房间信息
	 * @param rooms
	 * @return
	 */
	Integer insertRooms(Rooms rooms);
	/**
	 * 修改酒店房间信息
	 * @param rooms
	 * @return
	 */
	Integer updateRooms(Rooms rooms);
	/**
	 * 批量删除酒店房间信息
	 * @param roomIds
	 * @return
	 */
	Integer deleteRooms(String roomIds);
	
	List<Rooms> selectRoommunAll();
	
	/**
	 * 根据房间类别以及是否空闲查询房间信息（用于酒店预订管理）
	 * @param roomcategoryId
	 * @return
	 */
	List<Rooms> findRoomsByRoomcategoryIdandIsFree(Integer roomcategoryId);
}
