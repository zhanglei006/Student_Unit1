package com.ysd.demo.service;

import java.util.Map;

import com.ysd.demo.entity.RoomRuZhu;

public interface RoomRuZhuService {
	
	/**
	 * 分页多条件查询入住信息
	 * @param page
	 * @param rows
	 * @param roomRuZhu
	 * @return
	 */
	Map<String, Object> findAllRoomRuZhu(Integer page,Integer limit,RoomRuZhu roomRuZhu);
	
	/**
	 * 添加入住信息
	 * @param roomRuZhu
	 * @return
	 */
	Integer insertRoomRuZhu(RoomRuZhu roomRuZhu);
	
	/**
	 * 修改入住信息
	 * @param roomRuZhu
	 * @return
	 */
	Integer updateRoomRuZhu(RoomRuZhu roomRuZhu);
	
	/**
	 * 删除入住信息
	 * @param roomRuZhuId
	 * @return
	 */
	Integer deleteRoomRuZhu(Integer roomRuZhuId);
	
	/**
	 * 退房
	 * @param roomRuZhuId
	 * @return
	 */
	Integer updateRoomRuZhuTuiFang(Integer roomRuZhuId);
	
}
