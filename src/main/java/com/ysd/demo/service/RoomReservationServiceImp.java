package com.ysd.demo.service;

import java.util.Arrays;
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
import com.ysd.demo.entity.Pictures;
import com.ysd.demo.entity.RoomReservation;
import com.ysd.demo.entity.RoomRuZhu;
import com.ysd.demo.entity.Rooms;
import com.ysd.demo.mapper.RoomReservationMapper;
import com.ysd.demo.mapper.RoomRuZhuMapper;
import com.ysd.demo.mapper.RoomsMapper;
@Service
public class RoomReservationServiceImp implements RoomReservationService{
	
	@Autowired
	private RoomReservationMapper roomReservationMapper;
	@Autowired
	private RoomsMapper roomsMapper;
	@Autowired
	private RoomRuZhuMapper roomRuZhuMapper;
	
	
	@Override
	public Map<String, Object> findAllRoomReservation(Integer page, Integer limit, RoomReservation roomReservation) {
		QueryWrapper<RoomReservation> queryWrapper = new QueryWrapper<RoomReservation>();
		queryWrapper.exists(true, "select * from roomReservation")
					.like(!(StringUtils.isEmpty(roomReservation.getReservaName())), "reservaName", roomReservation.getReservaName())
					.eq(!(null==roomReservation.getReservastate()), "reservastate", roomReservation.getReservastate())
					.gt(!(StringUtils.isEmpty(roomReservation.getReservaStartTime())) , "reservaStartTime", roomReservation.getReservaStartTime())
					.lt(!(StringUtils.isEmpty(roomReservation.getReservaEndTime())) , "reservaEndTime", roomReservation.getReservaEndTime());
		Page<RoomReservation> page2 = new Page<RoomReservation>((page-1)*limit,limit);
		IPage<RoomReservation> selectMapsPage = roomReservationMapper.customizeSelectByRoomReservation(page2, queryWrapper);
		long total = selectMapsPage.getTotal();
		List<RoomReservation> records = selectMapsPage.getRecords();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0); 
	    map.put("msg"," ");
	    map.put("count", total);
	    map.put("data", records);
		return map;
	}
	
	@Transactional
	@Override
	public Integer insertRoomReservation(RoomReservation roomReservation) {
		// TODO Auto-generated method stub
		Integer reservastate = roomReservation.getReservastate();
		if(reservastate==1) {
			RoomRuZhu roomRuZhu = new RoomRuZhu();
			roomRuZhu.setRoomId(roomReservation.getRoomId());
			roomRuZhu.setRayamount(roomReservation.getRayamount());
			roomRuZhu.setStartTime(roomReservation.getReservaStartTime());
			roomRuZhu.setEndTime(roomReservation.getReservaEndTime());
			roomRuZhu.setRuZhuName(roomReservation.getReservaName());
			roomRuZhu.setRuZhuCard(roomReservation.getReservaCard());
			roomRuZhu.setPeoplenum(1);
			roomRuZhu.setRuzhustate(0);
			roomRuZhuMapper.insert(roomRuZhu);
		}
		Rooms rooms = new Rooms();
		rooms.setRoomId(roomReservation.getRoomId());
		rooms.setIsFree(1);
		roomsMapper.updateById(rooms);
		return roomReservationMapper.insert(roomReservation);
		
		
	}

	@Override
	@Transactional
	public Integer updateRoomReservation(RoomReservation roomReservation) {
		// TODO Auto-generated method stub
		QueryWrapper<RoomReservation> queryWrapper = new QueryWrapper<RoomReservation>();
		queryWrapper.eq("reservationId", roomReservation.getReservationId());
		List<RoomReservation> selectList = roomReservationMapper.selectList(queryWrapper);
		if(roomReservation.getReservastate()!=null&&roomReservation.getReservastate()==1) {
			RoomRuZhu roomRuZhu = new RoomRuZhu();
			roomRuZhu.setRoomId(selectList.get(0).getRoomId());
			roomRuZhu.setRayamount(selectList.get(0).getRayamount());
			roomRuZhu.setStartTime(selectList.get(0).getReservaStartTime());
			roomRuZhu.setEndTime(selectList.get(0).getReservaEndTime());
			roomRuZhu.setRuZhuName(selectList.get(0).getReservaName());
			roomRuZhu.setRuZhuCard(selectList.get(0).getReservaCard());
			roomRuZhu.setPeoplenum(1);
			roomRuZhu.setRuzhustate(0);
			roomRuZhuMapper.insert(roomRuZhu);
		}
		if(roomReservation.getRoomId()==null) {
			return roomReservationMapper.updateById(roomReservation);
		}else{
			Integer roomId = selectList.get(0).getRoomId();
			Rooms rooms = new Rooms();
			rooms.setRoomId(roomId);
			rooms.setIsFree(0);
			roomsMapper.updateById(rooms);
			rooms.setRoomId(roomReservation.getRoomId());
			rooms.setIsFree(1);
			roomsMapper.updateById(rooms);
			QueryWrapper<RoomRuZhu> queryWrapper2 = new QueryWrapper<RoomRuZhu>();
			queryWrapper2.eq("roomId", roomId);
			List<RoomRuZhu> selectList2 = roomRuZhuMapper.selectList(queryWrapper2);
			if(selectList2.size()>0) {
				Integer roomRuZhuId = selectList2.get(0).getRoomRuZhuId();
				RoomRuZhu roomRuZhu = new RoomRuZhu();
				roomRuZhu.setRoomId(roomReservation.getRoomId());
				roomRuZhu.setRoomRuZhuId(roomRuZhuId);
				roomRuZhuMapper.updateById(roomRuZhu);
			}
			return roomReservationMapper.updateById(roomReservation);
		}
	}

	@Override
	@Transactional
	public Integer deleteRoomReservation(String reservationIds) {
		String[] split = reservationIds.split(",");
		List<String> asList = Arrays.asList(split);
		// TODO Auto-generated method stub
		for(int i=0;i<asList.size();i++) {
			QueryWrapper<RoomReservation> queryWrapper = new QueryWrapper<RoomReservation>();
			queryWrapper.eq("reservationId",Integer.parseInt(asList.get(i)));
			List<RoomReservation> selectList = roomReservationMapper.selectList(queryWrapper);
			Integer roomId = selectList.get(0).getRoomId();
			Rooms rooms = new Rooms();
			rooms.setRoomId(roomId);
			rooms.setIsFree(0);
			roomsMapper.updateById(rooms);
			/*
			 * QueryWrapper<RoomRuZhu> queryWrapper2 = new QueryWrapper<RoomRuZhu>();
			 * queryWrapper2.eq("roomId", roomId); List<RoomRuZhu> selectList2 =
			 * roomRuZhuMapper.selectList(queryWrapper2); RoomRuZhu roomRuZhu =
			 * selectList2.get(0); roomRuZhu.setRuzhustate(1);
			 * roomRuZhuMapper.updateById(roomRuZhu);
			 */
			
		}
		return roomReservationMapper.deleteBatchIds(asList);
	}
	
}
