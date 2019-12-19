package com.ysd.demo.service;

import java.util.Map;

import com.ysd.demo.entity.RoomReservation;

public interface RoomReservationService {
	
	/**
	 * 分页多条件查询酒店预订信息
	 * @param page
	 * @param limit
	 * @param roomReservation
	 * @return
	 */
	Map<String, Object> findAllRoomReservation(Integer page,Integer limit,RoomReservation roomReservation);
		
	/**
	 * 添加酒店预订信息
	 * @param roomReservation
	 * @return
	 */
	Integer insertRoomReservation(RoomReservation roomReservation);
	
	/**
	 * 修改酒店预订信息
	 * @param roomReservation
	 * @return
	 */
	Integer updateRoomReservation(RoomReservation roomReservation);
	
	
	/**
	 * 删除酒店预订信息
	 * @param reservationId
	 * @return
	 */
	Integer deleteRoomReservation(String reservationIds);
}
