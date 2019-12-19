package com.ysd.demo.contorller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ysd.demo.entity.Pictures;
import com.ysd.demo.service.PicturesService;
@CrossOrigin
@RestController
public class PicturesController {
	
	@Autowired
	private PicturesService picturesService;
	
	private static Integer roommuns;//房间号（用于添加）
	
	private static Integer iszhutus;//是否设置为主图（用于添加）
	
	private Pictures pictures1;//用于确认替换主图的添加图片参数
	
	@PostMapping("/findAllPictures")
	public Map<String, Object> findAllPictures(Integer page, Integer limit) {
		return picturesService.findAllPictures(page, limit);
	}
	
	@PostMapping("/insertPictures")
	public Integer insertPictures(Pictures pictures,MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		System.out.println(originalFilename);
		String replace = originalFilename.replace(".jpg", "");//去除图片路径中的后缀.jpg
		String replaceAll = replace.replaceAll("\\d+","");//去除图片路径中的数字
		originalFilename = "img/"+replaceAll+"/"+originalFilename;//整理后要添加进数据库的图片路径
		pictures.setPicturesUrl(""+originalFilename);
		pictures.setRoomId(roommuns);
		pictures.setIszhutu(iszhutus);
		pictures1 = pictures;
		return picturesService.insertPictures(pictures);
	}
	
	
	
	
	/*
	 * 获取添加图片的房间编号
	 */
	@PostMapping("/insertroommun")
	public static Integer insertroommun(Integer roomId) {
		System.out.println("房间编号"+roomId);
		roommuns = roomId;
		return  roomId;
	}
	
	/*
	 * 获取添加图片是否为主图
	 */
	@PostMapping("/insertiszhutu")
	public static Integer insertiszhutu(Integer iszhutu) {
		System.out.println("是否这是主图"+iszhutu);
		iszhutus = iszhutu;
		return  iszhutu;
	}
	
	
	@PostMapping("/querentihuan")
	public Integer querentihuan() {
		return picturesService.querentihuan(pictures1);
	}
	

	@PostMapping("/quxiaotihuan")
	public Integer quxiaotihuan() {
		pictures1.setIszhutu(1);
		return picturesService.quxiaotihuan(pictures1);
	}
	
	
	
	
	@PostMapping("/updatePictures")
	public Integer updatePictures(Pictures pictures,MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		System.out.println(originalFilename);
		String replace = originalFilename.replace(".jpg", "");//去除图片路径中的后缀.jpg
		String replaceAll = replace.replaceAll("\\d+","");//去除图片路径中的数字
		originalFilename = "img/"+replaceAll+"/"+originalFilename;//整理后要添加进数据库的图片路径
		pictures.setPicturesUrl(""+originalFilename);
		return picturesService.updatePictures(pictures);
	}
	
	
	@PostMapping("/updatePicture")
	public Integer updatePicture(Pictures pictures,Integer roommun) {
		System.out.println("参数"+pictures+roommun);
		return picturesService.updatePicture(pictures,roommun);
	}
	
	
	@PostMapping("/deletePictures")
	public Integer deletePictures(Integer picturesId,Integer roomId) {
		return picturesService.deletePictures(picturesId, roomId);
	}
	
}
