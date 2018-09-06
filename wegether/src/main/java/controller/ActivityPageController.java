//6upackage controller;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import model.ActivityBean;
//import model.AttendBean;
//import model.MemberBean;
//import model.PictureBean;
//import model.dao.ActivityDAO;
//import model.dao.AttendDAO;
//import model.dao.MemberDAO;
//import model.dao.PictureDAO;
//import pictureconvert.PictureConvert;
//
//@Controller
//public class ActivityPageController {
//	@Autowired
//	private ActivityDAO activityDAO;
//	
//	@Autowired
//	private MemberDAO memberDAO;
//	
//	@Autowired
//	private PictureDAO pictureDAO;
//	
//	@Autowired
//	private AttendDAO attendDAO;
//	
//	@InitBinder
//	public void registerPropertyEditor(WebDataBinder webDataBinder) {
//		webDataBinder.registerCustomEditor(java.util.Date.class,
//				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));		
//	}
//	
//	@RequestMapping("/activityPage.controller")
//	public String method(Model model,String actid) {
//		System.out.println("actid="+actid);
//		List<String> actPicList = new ArrayList<>();
//		List<String> memPicList = new ArrayList<>();
//		List<String> attPicList = new ArrayList<>();
//		
//		ActivityBean actBean = activityDAO.selectId(Integer.parseInt(actid));
//		MemberBean memBean = memberDAO.select(actBean.getHostid());		
//		List<AttendBean> attBean = attendDAO.selectByActID(Integer.parseInt(actid));
//		
//		attBean.forEach(att->{
//			att.getMemberid();
//			List<PictureBean> attMemPicBean = pictureDAO.selectByMember(memBean.getId());	
//		});
//		
//		List<PictureBean> actPicBean = pictureDAO.selectByActivity(actBean.getId());		
//		List<PictureBean> memPicBean = pictureDAO.selectByMember(memBean.getId());
//		
//		//==========================================
//		
//		// Users u = daoUsers.find("hi.steven@gmail.com");
////		        for(Appointment a:u.getAppointments()) {
////		              System.out.println(a.toString());
////		         }
//		
//			//==========================================
//		actPicBean.forEach(pic->{
//			actPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
//		});
//		
//		memPicBean.forEach(pic->{
//			memPicList.add(PictureConvert.convertBase64Image(pic.getPicture()));
//		});
//
//		
//		model.addAttribute("actBean",actBean);
//		model.addAttribute("memBean",memBean);
//		model.addAttribute("actPicBean",actPicBean);
//		model.addAttribute("memPicBean",memPicBean);
//		model.addAttribute("actPicList",actPicList);
//		model.addAttribute("memPicList",memPicList);
//		
//		return "activityPage";
//	}
//}
