package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import Service.TrackmemberService;
import model.TrackmemberBean;

@Controller
@SessionAttributes(names = { "select" })
public class TrackmemberController {
	@Autowired
	private TrackmemberService trackmemberService;

	@RequestMapping(path = { "/Trackmember.insert" })
	protected void follow(Model model, int Memberid, HttpServletRequest request, HttpServletResponse response,
			@RequestAttribute(name = "memberid", required = false) Integer id) throws ServletException, IOException {
		Map<String,String>result1=null;
		TrackmemberBean result = null;
		System.out.println("id=" + id);// 使用者ID
		System.out.println(Memberid);
		int fid = id;
		int mid = 0; // 被追蹤ID 歸0
		 response.setContentType("text/html; charset=UTF-8");
		mid = Memberid;
		// 接收資料
//		if (request.getParameter("Memberid") != "") {
		if (request.getParameter("Memberid") != "") {
			 mid = Integer.valueOf(request.getParameter("Memberid"));
			 System.out.println("mid="+mid);
			 result = trackmemberService.insert(mid,fid);// 加追蹤
			 System.out.println("result結果="+result);
			 System.out.print(result);

			if (result != null) {
				 PrintWriter out = response.getWriter();
				 out.println("追蹤成功"+result);
				 out.close();
				model.addAttribute("result", result);
//				result1.put("result", "追蹤成功");
			} else {
				 PrintWriter out = response.getWriter();
				 out.println("追蹤失敗"+result);
				 out.close();
				model.addAttribute("result", result);
//				result1.put("result", "追蹤失敗");
			}
		}
//		if(result1!=null) {
//			model.addAttribute("output",result1);
//			return "Service.insertpage";
//		}
//		return "Service.insertpage";
	}

	@RequestMapping(path = { "/Trackmember.delete" })
	protected void unfollow(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestAttribute("memberid") Integer id) throws ServletException, IOException {
		System.out.println("id=" + id);// 使用者ID
		int fid = id;
		int mid = 0; // 被追蹤ID
		response.setContentType("text/html; charset=UTF-8");
		boolean result = false;
		// 接收資料

		if (request.getParameter("Memberid") != "") {
			mid = Integer.valueOf(request.getParameter("Memberid"));
			System.out.println(mid);
			result = trackmemberService.delete(mid, fid);// 取消追蹤
		}
		if (result != false) {
			PrintWriter out = response.getWriter();
			out.println("取消追蹤成功");
			out.close();
		} else {
			PrintWriter out = response.getWriter();
			out.println("未追蹤");
			out.close();
		}
		System.out.println(result);
	}
}
