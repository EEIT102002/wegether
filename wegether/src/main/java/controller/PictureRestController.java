package controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.ActivityBean;
import model.MemberBean;
import model.PictureBean;
import model.dao.ActivityDAO;
import model.dao.MemberDAO;
import model.dao.PictureDAO;

@RestController
public class PictureRestController {
	@Autowired
	PictureDAO pictureDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	ActivityDAO activityDAO;

	@GetMapping(value = "/picture/{id}", produces = { "image/jpg", "image/jpeg", "image/png" })
	public @ResponseBody byte[] getPicture(@PathVariable Integer id) {
		PictureBean bean = pictureDAO.Select(id);
		return bean.getPicture();
	}

	@GetMapping(value = "/member/photo/{id}", produces = { "image/jpg", "image/jpeg", "image/png" })
	public @ResponseBody byte[] getMemberPicture(@PathVariable Integer id) {
		MemberBean bean = memberDAO.select(id);
		return bean.getPhoto();
	}

	@GetMapping(value = "/activity/photo/{id}", produces = { "image/jpg", "image/jpeg", "image/png" })
	public @ResponseBody byte[] getActivityPicture(@PathVariable Integer id) {
		ActivityBean bean = activityDAO.selectId(id);
		return bean.getPicture();
	}

	@DeleteMapping(value = "/pictureD/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		boolean result = pictureDAO.delete(id);
		if (result) {
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}