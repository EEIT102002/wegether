package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import model.MemberBean;
import model.MemberInfoBean;
import model.dao.MemberDAO;
import model.dao.MemberInfoDAO;

@RestController
public class MemberInfoController {
	@Autowired
	MemberInfoDAO memberInfoDAO;
	
	
	@GetMapping("/member")
	public ResponseEntity<?> getBySignIn(){
		MemberInfoBean result = memberInfoDAO.select(1);
		System.out.println(result);
		
		
		if(result!=null) {
			return new ResponseEntity<MemberInfoBean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
