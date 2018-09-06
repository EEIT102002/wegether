package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.AttendBean;
import model.FriendBean;
import model.MemberBean;
import model.PictureBean;
import model.TrackmemberBean;
import model.dao.PictureDAO;
import model.dao.implement.AttendDAOHibernate;
import model.dao.implement.FriendDAOHibernate;
import model.dao.implement.MemberDAOHibernate;
import model.dao.implement.PictureDAOHibernate;
import model.dao.implement.TrackmemberDAOHibernate;
import pictureconvert.PictureConvert;


@Controller
//@SessionAttributes(names={"mem","picbean"})
public class MemberPersonalController {
	@Autowired
	private MemberDAOHibernate memberDaoHibernate;
	@Autowired
	private FriendDAOHibernate friendDaoHibernate;
	@Autowired
	private AttendDAOHibernate attendDaoHibernate;
	@Autowired
	private TrackmemberDAOHibernate trackmemberDaoHibernate;
	@Autowired
	private PictureDAOHibernate pictureDaoHibernate;
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
	}
	@RequestMapping(
			path={"/personal.controller"}
	)
	public String processTest(Model model,MemberBean bean) {
		
		MemberBean membean = memberDaoHibernate.select(1);
		List<FriendBean> fribean= friendDaoHibernate.select(membean.getId());
		List<AttendBean>attbean= attendDaoHibernate.selectBymemberid(membean.getId());
		List<TrackmemberBean>trackbean= trackmemberDaoHibernate.selectBymemberid(membean.getId());
		
		List<String> memPicList = new ArrayList<>();
     	List<PictureBean> picbean = pictureDaoHibernate.selectByMember(membean.getId());
     	picbean.forEach(pic->{
     		memPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
     		
     	});
     	
//		System.out.println(trackbean);
	   // System.out.println(trackbean.size());
		model.addAttribute("mem",membean);
		model.addAttribute("fribean",fribean.size());
		model.addAttribute("attbean",attbean.size());
		model.addAttribute("trackbean",trackbean.size());
		model.addAttribute("picbean", memPicList);
		//System.out.println("2");
		return "personal.success";
	}

}