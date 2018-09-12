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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import Service.BlackListService;
import model.BlacklistBean;

@Controller
public class BlackListController {
	@Autowired
	private BlackListService blackListService;

	@RequestMapping(path = { "/BlackList.insert" })
	protected void doGet(Model model, HttpServletRequest request, HttpServletResponse response,@RequestAttribute("memberid") Integer id)
			throws ServletException, IOException {
		List<BlacklistBean> result = null;
		System.out.print("mid="+id);//userid
		int mid =id;
		int bid = 0;         //被黑名單
		response.setContentType("text/html; charset=UTF-8");

		// 接收資料
		if (request.getParameter("blackid") != "") {
			bid = Integer.valueOf(request.getParameter("blackid"));
			System.out.println("bid="+bid);
			 result = blackListService.insert(bid,mid);// 加黑名單
			 }
			 if (result != null) {
			 PrintWriter out = response.getWriter();
			 out.println("加黑名單成功");
			 out.close();
			 } else {
			 PrintWriter out = response.getWriter();
			 out.println("加黑名單失敗");
			 out.close();
			 }
			 System.out.println(result);
		}


	@RequestMapping(path = { "/BlackList.delete" })
	protected void doGet1(Model model, HttpServletRequest request, HttpServletResponse response,@RequestAttribute("memberid") Integer id)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		boolean result = false;
		System.out.print("mid="+id);//userid
		int mid =id;
		int bid = 0;         //被黑名單
		// 接收資料

		if (request.getParameter("blackid") != "") {
			bid = Integer.valueOf(request.getParameter("blackid"));
			System.out.println("取消對"+bid+"黑名單");
			result = blackListService.delete(bid,mid);// 取消追蹤
		}
		if (result != false) {
			PrintWriter out = response.getWriter();
			out.println("取消黑名單成功");
			out.close();
		} else {
			PrintWriter out = response.getWriter();
			out.println("未在黑名單");
			out.close();
		}
		System.out.println(result);
	}
}
