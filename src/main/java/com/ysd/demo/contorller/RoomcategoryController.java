package com.ysd.demo.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.demo.entity.Roomcategory;
import com.ysd.demo.service.RoomcategoryService;
@CrossOrigin
@RestController
public class RoomcategoryController {
	@Autowired
	private RoomcategoryService roomcategoryService;
	
	@PostMapping("/selectRoomcategoryAll")
	public List<Roomcategory> selectRoomcategoryAll(){
		return roomcategoryService.selectRoomcategoryAll();
	}
	
	@PostMapping("/findAllRoomcategory")
	public Map<String, Object> findAllRoomcategory(Integer page,Integer limit) {
		return roomcategoryService.findAllRoomcategory(page,limit);
	}
	
	@PostMapping("/insertRoomcategory")
	public Integer insertRoomcategory(Roomcategory roomcategory) {
		return roomcategoryService.insertRoomcategory(roomcategory);
	}
	@PostMapping("/updateRoomcategory")
	public Integer updateRoomcategory(Roomcategory roomcategory) {
		return roomcategoryService.updateRoomcategory(roomcategory);
	}
	
	@PostMapping("/deleteRoomcategory")
	public Integer deleteRoomcategory(Integer roomcategoryId) {
		return roomcategoryService.deleteRoomcategory(roomcategoryId);
	}
}
