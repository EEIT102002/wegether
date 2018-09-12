package controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.MemberSearchService;

@Controller
public class MemberSearchController {
	@Autowired
	MemberSearchService memberSearchService;
	
	@RequestMapping(path= {"/member/search/nickname"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> searchByNickname(String nickname, HttpServletRequest request){
		if(nickname == null || nickname.trim().length() <=0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		JSONArray result = memberSearchService.searchByNickname(nickname);
		
		if(result != null) {	
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path= {"/member/search/nickname/friend"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> searchByNicknameForFrined(
			@RequestAttribute("memberid") Integer id, String nickname, HttpServletRequest request){
		if(nickname == null || nickname.trim().length() <=0 || id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		JSONArray result = memberSearchService.searchByNicknameForFriend(nickname, id);
		System.out.println("nickname: "+nickname);
		System.out.println("id: "+id);
		if(result != null) {
			System.out.println(result.toString());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			System.out.println("false");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path= {"/member/search/nickname/track"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> searchByNicknameForTrack(
			@RequestAttribute("memberid") Integer id, String nickname, HttpServletRequest request){
		if(nickname == null || nickname.trim().length() <=0 || id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		JSONArray result = memberSearchService.searchByNicknameForTrack(nickname, id);
		
		if(result != null) {	
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path= {"/member/search/nickname/blacklist"}, produces= {"application/json"})
	public @ResponseBody ResponseEntity<?> searchByNicknameForBlacklist(
			@RequestAttribute("memberid") Integer id, String nickname, HttpServletRequest request){
		if(nickname == null || nickname.trim().length() <=0 || id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		JSONArray result = memberSearchService.searchByNicknameForBlacklist(nickname, id);
		
		if(result != null) {	
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
