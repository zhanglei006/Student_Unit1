package com.ysd.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysd.demo.entity.RoomReservation;
import com.ysd.demo.entity.RoomRuZhu;
import com.ysd.demo.entity.Rooms;
import com.ysd.demo.mapper.RoomReservationMapper;
import com.ysd.demo.mapper.RoomRuZhuMapper;
import com.ysd.demo.mapper.RoomsMapper;
@Service
public class RoomRuZhuServiceImp implements RoomRuZhuService{
	
	@Autowired
	private RoomRuZhuMapper roomRuZhuMapper;
	@Autowired
	private RoomsMapper roomsMapper;
	@Autowired
	private RoomReservationMapper roomReservationMapper;
	
	
	@Override
	public Map<String, Object> findAllRoomRuZhu(Integer page, Integer limit, RoomRuZhu roomRuZhu) {
		// TODO Auto-generated method stub
		QueryWrapper<RoomRuZhu> queryWrapper = new QueryWrapper<RoomRuZhu>();
		queryWrapper.exists(true, "select * from roomRuZhu")
					.like(!(StringUtils.isEmpty(roomRuZhu.getRuZhuName())), "ruZhuName", roomRuZhu.getRuZhuName())
					.eq(roomRuZhu.getRuzhustate()!=null, "ruzhustate", roomRuZhu.getRuzhustate())
					.gt(!(StringUtils.isEmpty(roomRuZhu.getStartTime())) , "startTime", roomRuZhu.getStartTime())
					.lt(!(StringUtils.isEmpty(roomRuZhu.getEndTime())) , "endTime", roomRuZhu.getEndTime());
		Page<RoomRuZhu> page2 = new Page<RoomRuZhu>((page-1)*limit,limit);
		IPage<RoomRuZhu> selectMapsPage = roomRuZhuMapper.customizeSelectByRoomRuZhu(page2, queryWrapper);
		long total = selectMapsPage.getTotal();
		List<RoomRuZhu> records = selectMapsPage.getRecords();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0); 
	    map.put("msg"," ");
	    map.put("count", total);
	    map.put("data", records);
		return map;
	}
	
	@Transactional
	@Override
	public Integer insertRoomRuZhu(RoomRuZhu roomRuZhu) {
		// TODO Auto-generated method stub
		QueryWrapper<Rooms> queryWrapper = new QueryWrapper<Rooms>();
		queryWrapper.eq("roomId", roomRuZhu.getRoomId());
		List<Rooms> selectList = roomsMapper.selectList(queryWrapper);
		Rooms rooms = new Rooms();
		rooms.setRoomId(selectList.get(0).getRoomId());
		rooms.setIsFree(1);
		roomsMapper.updateById(rooms);
		roomRuZhu.setRuzhustate(0);
		return roomRuZhuMapper.insert(roomRuZhu);
	}
	
	@Transactional
	@Override
	public Integer updateRoomRuZhu(RoomRuZhu roomRuZhu) {
		// TODO Auto-generated method stub
		if(roomRuZhu.getRoomId()!=null) {
			QueryWrapper<RoomRuZhu> queryWrapper = new QueryWrapper<RoomRuZhu>();
			queryWrapper.eq("roomRuZhuId", roomRuZhu.getRoomRuZhuId());
			List<RoomRuZhu> selectList = roomRuZhuMapper.selectList(queryWrapper);
			Integer roomId = selectList.get(0).getRoomId();
			Rooms rooms = new Rooms();
			rooms.setRoomId(roomId);
			rooms.setIsFree(0);
			roomsMapper.updateById(rooms);
			rooms.setRoomId(roomRuZhu.getRoomId());
			rooms.setIsFree(1);
			roomsMapper.updateById(rooms);
			QueryWrapper<RoomReservation> queryWrapper2 = new QueryWrapper<RoomReservation>();
			queryWrapper2.eq("roomId", roomId);
			List<RoomReservation> selectList2 = roomReservationMapper.selectList(queryWrapper2);
			if(selectList2.size()>0) {
				Integer reservationId = selectList2.get(0).getReservationId();
				RoomReservation roomReservation = new RoomReservation();
				roomReservation.setRoomId(roomRuZhu.getRoomId());
				roomReservation.setReservationId(reservationId);
				roomReservationMapper.updateById(roomReservation);
			}
		}
		return roomRuZhuMapper.updateById(roomRuZhu);
	}
	
	
	@Override
	public Integer deleteRoomRuZhu(Integer roomRuZhuId) {
		// TODO Auto-generated method stub
		return roomRuZhuMapper.deleteById(roomRuZhuId);
	}
	
	@Transactional
	@Override
	public Integer updateRoomRuZhuTuiFang(Integer roomRuZhuId) {
		// TODO Auto-generated method stub
		RoomRuZhu roomRuZhu = new RoomRuZhu();
		roomRuZhu.setRoomRuZhuId(roomRuZhuId);
		roomRuZhu.setRuzhustate(1);
		int updateById = roomRuZhuMapper.updateById(roomRuZhu);
		QueryWrapper<RoomRuZhu> queryWrapper = new QueryWrapper<RoomRuZhu>();
		queryWrapper.eq("roomRuZhuId", roomRuZhuId);
		List<RoomRuZhu> selectList = roomRuZhuMapper.selectList(queryWrapper);
		Integer roomId = selectList.get(0).getRoomId();
		Rooms rooms = new Rooms();
		rooms.setRoomId(roomId);
		rooms.setIsFree(0);
		roomsMapper.updateById(rooms);
		QueryWrapper<RoomReservation> queryWrapper2 = new QueryWrapper<RoomReservation>();
		queryWrapper2.eq("roomId", roomId);
		List<RoomReservation> selectList2 = roomReservationMapper.selectList(queryWrapper2);
		roomReservationMapper.deleteById(selectList2.get(0).getReservationId());
		
		
		return updateById;
	}
	
	
}
