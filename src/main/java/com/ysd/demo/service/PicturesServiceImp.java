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
import com.ysd.demo.entity.Pictures;
import com.ysd.demo.entity.Rooms;
import com.ysd.demo.mapper.PicturesMapper;
import com.ysd.demo.mapper.RoomsMapper;

@Service
public class PicturesServiceImp implements PicturesService{
	@Autowired
	private PicturesMapper picturesMapper;
	@Autowired
	private RoomsMapper roomsMapper;
	
	@Override
	public Map<String, Object> findAllPictures(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		QueryWrapper<Pictures> queryWrapper = new QueryWrapper<Pictures>();
		queryWrapper.exists(true, "select * from pictures");
		Page<Pictures> page2 = new Page<Pictures>();
		IPage<Pictures> selectPage = picturesMapper.customizeSelectBypictures(page2, queryWrapper);
		long total = selectPage.getTotal();
		List<Pictures> records = selectPage.getRecords();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0); 
	    map.put("msg"," ");
	    map.put("count", total);
	    map.put("data", records);
		return map;
	}

	@Override
	public Integer insertPictures(Pictures pictures) {
		// TODO Auto-generated method stub
		if(pictures.getIszhutu()==0) {
			QueryWrapper<Pictures> queryWrapper = new QueryWrapper<Pictures>();
			queryWrapper.eq("iszhutu", pictures.getIszhutu())
						.eq("roomId", pictures.getRoomId());
			List<Pictures> selectList = picturesMapper.selectList(queryWrapper);
			if(selectList.size()==0) {
				int insert = picturesMapper.insert(pictures);
				return insert;
			}else {
				return 0;
			}
		}else {
			int insert = picturesMapper.insert(pictures);
			return insert;
		}
		
	}

	@Override
	public Integer updatePictures(Pictures pictures) {
		// TODO Auto-generated method stub
		return picturesMapper.updateById(pictures);
	}

	@Override
	public Integer deletePictures(Integer picturesId,Integer roomId) {
		// TODO Auto-generated method stub
		QueryWrapper<Pictures> queryWrapper = new QueryWrapper<Pictures>();
		queryWrapper.eq("roomId", roomId);
		List<Pictures> selectList = picturesMapper.selectList(queryWrapper);
		if(selectList.size()>1) {
			return 0;
		}
		else {
			return picturesMapper.deleteById(picturesId);
		}
	}
	
	@Transactional
	@Override
	public Integer querentihuan(Pictures pictures) {
		// TODO Auto-generated method stub
		QueryWrapper<Pictures> queryWrapper = new QueryWrapper<Pictures>();
		queryWrapper.eq("iszhutu", pictures.getIszhutu())
					.eq("roomId", pictures.getRoomId());
		List<Pictures> selectList = picturesMapper.selectList(queryWrapper);
		Pictures pictures2 = selectList.get(0);
		pictures2.setIszhutu(1);
		int updateById = picturesMapper.updateById(pictures2);
		int insert = picturesMapper.insert(pictures);
		return insert;
	}

	@Override
	public Integer quxiaotihuan(Pictures pictures) {
		// TODO Auto-generated method stub
		return picturesMapper.insert(pictures);
	}
	
	@Transactional
	@Override
	public Integer updatePicture(Pictures pictures,Integer roommun) {
		// TODO Auto-generated method stub
		QueryWrapper<Rooms> queryWrapper1 = new QueryWrapper<Rooms>();
		queryWrapper1.eq("roommun", roommun);
		List<Rooms> selectList2 = roomsMapper.selectList(queryWrapper1);
		if(pictures.getIszhutu()==0) {
			QueryWrapper<Pictures> queryWrapper = new QueryWrapper<Pictures>();
			queryWrapper.eq("iszhutu", pictures.getIszhutu())
						.eq("roomId", selectList2.get(0).getRoomId());
			List<Pictures> selectList = picturesMapper.selectList(queryWrapper);
			if(selectList.size()==0) {
				int insert = picturesMapper.updateById(pictures);
				return insert;
			}else {
				Pictures pictures2 = selectList.get(0);
				pictures2.setIszhutu(1);
				System.out.println("原主图"+pictures2);
				picturesMapper.updateById(pictures2);
				int insert = picturesMapper.updateById(pictures);
				return insert;
			}
		}else {
			int update = picturesMapper.updateById(pictures);
			return update;
		}
	}
	
}
