package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import Service.ServiceService;
import model.ServiceBean;
import sun.security.action.PutAllAction;

@Controller
@SessionAttributes(names = { "select", "action" })
public class ServiceController {
	@Autowired
	private ServiceService serviceService;

	@RequestMapping(path = { "/Service.controller" })
	public String method(Model model, String servicemethod,  @RequestAttribute(name = "memberid",required = false) Integer id, ServiceBean bean,
			BindingResult bindingResult) {
		System.out.println("Service.controller");
		System.out.println("id=" + id); // 取得登入的memberid

		// 接收資料
		// 轉換資料
		HashMap<String, String> errors = new HashMap<>();
		
		
		// if (bindingResult != null && bindingResult.hasFieldErrors()) {
		//// if (bindingResult.hasFieldErrors("memberid")) {
		//// errors.put("memberid", "memberid must be an integer");
		//// }
		//
		// if (bindingResult.hasFieldErrors("title")) {
		// errors.put("title", "title must write");
		// }
		//
		// if (bindingResult.hasFieldErrors("classtype")) {
		// errors.put("classtype", "classtype must select");
		// }
		//
		// if (bindingResult.hasFieldErrors("Content")) {
		// errors.put("Content", "Content must write");
		// }
		// }

		// 驗證資料
		// if("insert".equals(servicemethod)) {
		// if (temp1 == null || temp1.length() == 0) {
		// errors.put("memberid", "請先登入 " + servicemethod+" (FormBean)");
		// }
		// }
		
		
		
//		if (errors != null && !errors.isEmpty()) {
//			return "Service.errors";
//		}
		
		//登入驗證
		if (id==null) {
			System.out.println("未登入");
			errors.put("LoginError", "請先登入");
			System.out.println(errors.get("LoginError"));
			model.addAttribute("errors", errors);
			return "Service.errors";
		}else
			
		//查詢
		if ("Select".equals(servicemethod)) {
			bean.setMemberid(id); // 把memberid寫入到bean
			System.out.println(bean);
			List<ServiceBean> result = serviceService.select(bean);
			
			if (result != null) {
				System.out.println(result.size());
				if (result.size() != 0) {
					model.addAttribute("select", result);
					return "Service.List";
				} else {
					errors.put("SelectResult", "查無資料");
					model.addAttribute("SelectError", errors);
					return "Service.List";
				}
			}
			return "Service.errors";

		}
		if ("提交".equals(servicemethod)) {
			// bean.setAsktime(new java.util.Date());
			bean.setMemberid(id); // 把memberid寫入到bean
			System.out.println(bean);

			ServiceBean result = serviceService.insert(bean);// 寫入DB

			if (result == null) {

				System.out.println("false");
				errors.put("error", "提交失敗");
				return "Service.errors";
			} else {
				System.out.println("succest");
				List<ServiceBean> insertresult = serviceService.select(bean);
				model.addAttribute("select", insertresult);
				return "Service.List";
			}
		} else if ("更新".equals(servicemethod)) {
			// bean.setAsktime(new java.util.Date());
			bean.setAsktime(new java.util.Date());// 更新編輯時間
			bean.setMemberid(id); // 把memberid寫入到bean
			System.out.println(bean);

			ServiceBean result = serviceService.update(bean);// update到資料庫

			List<ServiceBean> insertresult = serviceService.select(bean);// 列出清單
			model.addAttribute("select", insertresult);
			return "Service.List";
		}
		errors.put("action", "Unknown Action:" + servicemethod);
		
		model.addAttribute("errors", errors);
		return "Service.errors";
		
	}

	@RequestMapping(path = { "/ServiceSelect.controller" })
	public String selectMethod(Model model, @RequestAttribute("memberid") Integer id, ServiceBean bean) {
		bean.setMemberid(id);
		List<ServiceBean> result = serviceService.select(bean);
		model.addAttribute("select", result);
		
//		model.addAttribute("errors", errors);
		return "Service.List";
	}
}
