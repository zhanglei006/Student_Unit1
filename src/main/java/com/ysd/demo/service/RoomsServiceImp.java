package com.ysd.demo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysd.demo.entity.Pictures;
import com.ysd.demo.entity.Rooms;
import com.ysd.demo.mapper.PicturesMapper;
import com.ysd.demo.mapper.RoomsMapper;
@Service

public class RoomsServiceImp implements RoomsService{
	@Autowired
	private RoomsMapper roomsMapper;
	@Autowired
	private PicturesMapper picturesMapper;
	
	@Override
	public Map<String, Object> selectRoomsAll(Integer page, Integer limit, Rooms rooms) {
		// TODO Auto-generated method stub
		QueryWrapper<Rooms> queryWrapper = new QueryWrapper<Rooms>();
		queryWrapper.exists(true, "select * from rooms")
					.eq(!(null==rooms.getRoomId()), "roomId", rooms.getRoomId())
					.eq(!(null==rooms.getRoomFloors()), "roomFloors", rooms.getRoomFloors())
					.eq(!(null==rooms.getIswindow()), "Iswindow", rooms.getIswindow())
					.eq(!(null==rooms.getIsWifi()), "IsWifi", rooms.getIsWifi())
					.eq(!(null==rooms.getAfewhuman()), "afewhuman", rooms.getAfewhuman())
					.eq(!(null==rooms.getIsFree()), "IsFree", rooms.getIsFree())
					.eq(!(null==rooms.getRoomcategoryId()), "r.roomcategoryId", rooms.getRoomcategoryId());
		Page<Rooms> page2 = new Page<Rooms>((page-1)*limit,limit);
		IPage<Rooms> selectMapsPage = roomsMapper.customizeSelectByRooms(page2, queryWrapper);
		long total = selectMapsPage.getTotal();
		List<Rooms> records = selectMapsPage.getRecords();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0); 
	    map.put("msg"," ");
	    map.put("count", total);
	    map.put("data", records);
		return map;
	}
	
	@Transactional
	@Override
	public Integer insertRooms(Rooms rooms) {
		// TODO Auto-generated method stub
		rooms.setIsFree(0);
		int insert = roomsMapper.insert(rooms);
		QueryWrapper<Rooms> queryWrapper = new QueryWrapper<Rooms>();
		queryWrapper.orderByDesc("roomId");
		List<Rooms> selectList = roomsMapper.selectList(queryWrapper);
		Integer roomId = selectList.get(0).getRoomId();
		Pictures pictures = new Pictures();
		pictures.setPicturesUrl("img/biaozhun/biaozhun1.jpg");
		pictures.setRoomId(roomId);
		pictures.setIszhutu(0);
		int insert2 = picturesMapper.insert(pictures);
		if(insert2<0) {
			return 0;
		}
		return insert;
	}
	
	@Override
	public Integer updateRooms(Rooms rooms) {
		// TODO Auto-generated method stub
		return roomsMapper.updateById(rooms);
	}
	@Transactional
	@Override
	public Integer deleteRooms(String roomIds) {
		String[] split = roomIds.split(",");
		List<String> asList = Arrays.asList(split);
		// TODO Auto-generated method stub
		QueryWrapper<Pictures> queryWrapper = new QueryWrapper<Pictures>();
		queryWrapper.in("roomId", asList);
		List<Pictures> selectList = picturesMapper.selectList(queryWrapper);
		for(int i=0;i<selectList.size();i++) {
			picturesMapper.deleteById(selectList.get(i).getPicturesId());
		}
		return roomsMapper.deleteBatchIds(asList);
	}

	@Override
	public List<Rooms> selectRoommunAll() {
		// TODO Auto-generated method stub
		QueryWrapper<Rooms> queryWrapper = new QueryWrapper<Rooms>();
		List<Rooms> selectList = roomsMapper.selectList(queryWrapper);
		return selectList;
	}

	@Override
	public List<Rooms> findRoomsByRoomcategoryIdandIsFree(Integer roomcategoryId) {
		// TODO Auto-generated method stub
		Rooms rooms = new Rooms();
		rooms.setRoomcategoryId(roomcategoryId);
		rooms.setIsFree(0);
		QueryWrapper<Rooms> queryWrapper = new QueryWrapper<Rooms>();
		queryWrapper.eq(!(rooms.getRoomcategoryId()==null), "roomcategoryId", rooms.getRoomcategoryId())
					.eq("isFree", rooms.getIsFree());
		List<Rooms> selectList = roomsMapper.selectList(queryWrapper);
		return selectList;
	}

}
