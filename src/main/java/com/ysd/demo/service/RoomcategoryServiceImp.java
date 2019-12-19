package com.ysd.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysd.demo.entity.Roomcategory;
import com.ysd.demo.entity.Rooms;
import com.ysd.demo.mapper.RoomcategoryMapper;
import com.ysd.demo.mapper.RoomsMapper;

@Service
public class RoomcategoryServiceImp implements RoomcategoryService{
	@Autowired
	private RoomcategoryMapper roomcategoryMapper;
	@Autowired
	private RoomsMapper roomsMapper;
	
	@Override
	public List<Roomcategory> selectRoomcategoryAll() {
		// TODO Auto-generated method stub
		QueryWrapper<Roomcategory> queryWrapper = new QueryWrapper<Roomcategory>();
		return roomcategoryMapper.selectList(queryWrapper);
	}

	@Override
	public Map<String, Object> findAllRoomcategory(Integer page,Integer limit) {
		// TODO Auto-generated method stub
		Page<Roomcategory> page2 = new Page<Roomcategory>((page-1)*limit,limit);
		QueryWrapper<Roomcategory> queryWrapper = new QueryWrapper<Roomcategory>();
		IPage<Roomcategory> selectPage = roomcategoryMapper.selectPage(page2, queryWrapper);
		List<Roomcategory> records = selectPage.getRecords();
		long total = selectPage.getTotal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0); 
	    map.put("msg"," ");
	    map.put("count", total);
	    map.put("data", records);
		return map;
	}

	@Override
	public Integer insertRoomcategory(Roomcategory roomcategory) {
		// TODO Auto-generated method stub
		QueryWrapper<Roomcategory> queryWrapper = new QueryWrapper<Roomcategory>();
		queryWrapper.eq("roomcategoryName", roomcategory.getRoomcategoryName());
		List<Roomcategory> selectList = roomcategoryMapper.selectList(queryWrapper);
		if(selectList.size()==0) {
			return roomcategoryMapper.insert(roomcategory);
		}else {
			return 0;
		}
	}

	@Override
	public Integer updateRoomcategory(Roomcategory roomcategory) {
		// TODO Auto-generated method stub
		QueryWrapper<Roomcategory> queryWrapper = new QueryWrapper<Roomcategory>();
		queryWrapper.eq("roomcategoryName", roomcategory.getRoomcategoryName());
		List<Roomcategory> selectList = roomcategoryMapper.selectList(queryWrapper);
		if(selectList.size()==0) {
			return roomcategoryMapper.updateById(roomcategory);
		}else {
			return 0;
		}
	}
	
	@Override
	public Integer deleteRoomcategory(Integer roomcategoryId) {
		// TODO Auto-generated method stub
		QueryWrapper<Rooms> queryWrapper = new QueryWrapper<Rooms>();
		queryWrapper.eq("roomcategoryId", roomcategoryId);
		List<Rooms> selectList = roomsMapper.selectList(queryWrapper);
		if(selectList.size()!=0) {
			return 0;
		}else {
			int deleteById = roomcategoryMapper.deleteById(roomcategoryId);
			return deleteById;
		}
		
	}

}
