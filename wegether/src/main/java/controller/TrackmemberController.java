package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	protected void doGet(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		// 接收資料

		int mid = Integer.valueOf(request.getParameter("Memberid"));
		System.out.println(mid);
		List<TrackmemberBean> result = trackmemberService.insert(mid);// 加追蹤

		if (result != null) {
			PrintWriter out = response.getWriter();
			out.println("追蹤成功");
			out.close();
		} else {
			PrintWriter out = response.getWriter();
			out.println("以追蹤");
			out.close();
		}
		System.out.println(result);
	}

	@RequestMapping(path = { "/Trackmember.delete" })
	protected void doGet1(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		boolean result = false;
		// 接收資料
		int mid = Integer.valueOf(request.getParameter("Memberid"));
		System.out.println(mid);
		result = trackmemberService.delete(mid);
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
