package com.ysd.demo.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.demo.entity.Rooms;
import com.ysd.demo.service.RoomsService;
@RestController
@CrossOrigin
public class RoomsContorller {
	@Autowired
	private RoomsService roomsService;
			
	@PostMapping("/selectRoomsAll")
	public Map<String, Object> selectRoomsAll(Integer page,Integer limit,Rooms rooms){
		return roomsService.selectRoomsAll(page, limit, rooms);
	}
	
	@PostMapping("/insertRooms")
	public Integer insertRooms(Rooms rooms) {
		return roomsService.insertRooms(rooms);
	}
	@PostMapping("/updateRooms")
	public Integer updateRooms(Rooms rooms) {
		return roomsService.updateRooms(rooms);
	}
	@PostMapping("/deleteRooms")
	public Integer deleteRooms(String roomIds) {
		return roomsService.deleteRooms(roomIds);
	}
	
	@PostMapping("/selectRoommunAll")
	public List<Rooms> selectRoommunAll() {
		return roomsService.selectRoommunAll();
	}
	
	
	@PostMapping("/findRoomsByRoomcategoryIdandIsFree")
	public List<Rooms> findRoomsByRoomcategoryIdandIsFree(Integer roomcategoryId){
		return roomsService.findRoomsByRoomcategoryIdandIsFree(roomcategoryId);
	}
	
}
