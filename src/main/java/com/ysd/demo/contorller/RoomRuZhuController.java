package com.ysd.demo.contorller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysd.demo.entity.RoomRuZhu;
import com.ysd.demo.service.RoomRuZhuService;
@CrossOrigin
@RestController
public class RoomRuZhuController {
	
	@Autowired
	private RoomRuZhuService roomRuZhuService;
	
	@PostMapping("/findAllRoomRuZhu")
	public Map<String, Object> findAllRoomRuZhu(Integer page, Integer limit, RoomRuZhu roomRuZhu) {
		return roomRuZhuService.findAllRoomRuZhu(page, limit, roomRuZhu);
	}
	
	@PostMapping("/insertRoomRuZhu")
	public Integer insertRoomRuZhu(RoomRuZhu roomRuZhu) {
		return roomRuZhuService.insertRoomRuZhu(roomRuZhu);
	}
	
	@PostMapping("/updateRoomRuZhu")
	public Integer updateRoomRuZhu(RoomRuZhu roomRuZhu) {
		return roomRuZhuService.updateRoomRuZhu(roomRuZhu);
	}
	
	
	@PostMapping("/deleteRoomRuZhu")
	public Integer deleteRoomRuZhu(Integer roomRuZhuId) {
		return roomRuZhuService.deleteRoomRuZhu(roomRuZhuId);
	}
	
	@PostMapping("/updateRoomRuZhuTuiFang")
	public Integer updateRoomRuZhuTuiFang(Integer roomRuZhuId) {
		return roomRuZhuService.updateRoomRuZhuTuiFang(roomRuZhuId);
	}
	
	
}
