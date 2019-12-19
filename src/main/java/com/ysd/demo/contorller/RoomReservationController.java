package com.ysd.demo.contorller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ysd.demo.entity.Pictures;
import com.ysd.demo.entity.RoomReservation;
import com.ysd.demo.service.RoomReservationService;

@RestController
@CrossOrigin
public class RoomReservationController {
	
	@Autowired
	private RoomReservationService roomReservationService;
	
	@PostMapping("/findAllRoomReservation")
	public Map<String, Object> findAllRoomReservation(Integer page, Integer limit, RoomReservation roomReservation) {
		return roomReservationService.findAllRoomReservation(page, limit, roomReservation);
	}
	
	@PostMapping("/insertRoomReservation")
	public Integer insertRoomReservation(RoomReservation roomReservation) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format2 = format.format(new Date());
		roomReservation.setReservaTime(format2);
		return roomReservationService.insertRoomReservation(roomReservation);
	}
	
	@PostMapping("/updateRoomReservation")
	public Integer updateRoomReservation(RoomReservation roomReservation) {
		return roomReservationService.updateRoomReservation(roomReservation);
	}
	
	@PostMapping("/deleteRoomReservation")
	public Integer deleteRoomReservation(String reservationIds) {
		return roomReservationService.deleteRoomReservation(reservationIds);
	}
	
}
