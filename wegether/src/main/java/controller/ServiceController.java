package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import Service.ServiceService;
import model.ServiceBean;
import model.dao.ServiceDAO;

@Controller
@SessionAttributes(names = { "select", "action" })
public class ServiceController {
	@Autowired
	private ServiceService serviceService;

	@Autowired
	private ServiceDAO serviceDAO;

	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

		// webDataBinder.registerCustomEditor(double.class, "price",
		// new PrimitiveNumberEditor(Double.class, true));
		//
		// webDataBinder.registerCustomEditor(int.class,
		// new PrimitiveNumberEditor(Integer.class, true));

		// webDataBinder.registerCustomEditor(double.class,
		// new PrimitiveNumberEditor(Double.class, true));
		//
		// webDataBinder.registerCustomEditor(int.class,
		// new PrimitiveNumberEditor(Integer.class, true));
	}

	@RequestMapping(path = { "/Service.controller" })
	public String method(Model model, String servicemethod, ServiceBean bean, BindingResult bindingResult,
			@RequestParam("memberid") String temp1) {
		System.out.println("Service.controller");

		// 接收資料
		// 轉換資料
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errors", errors);

//		 if (bindingResult != null && bindingResult.hasFieldErrors()) {
////		 if (bindingResult.hasFieldErrors("memberid")) {
////		 errors.put("memberid", "memberid must be an integer");
////		 }
//		
//		 if (bindingResult.hasFieldErrors("title")) {
//		 errors.put("title", "title must write");
//		 }
//		
//		 if (bindingResult.hasFieldErrors("classtype")) {
//		 errors.put("classtype", "classtype must select");
//		 }
//		
//		 if (bindingResult.hasFieldErrors("Content")) {
//		 errors.put("Content", "Content must write");
//		 }
//		 }

		// 驗證資料
		// if("insert".equals(servicemethod)) {
		// if (temp1 == null || temp1.length() == 0) {
		// errors.put("memberid", "請先登入 " + servicemethod+" (FormBean)");
		// }
		// }

		if (errors != null && !errors.isEmpty()) {
			return "Service.errors";
		}

		if ("Select".equals(servicemethod)) {
			List<ServiceBean> result = serviceService.select(bean);
			if (result != null) {
				
				System.out.println(result.size());
				if (result.size()!=0) {
					model.addAttribute("select", result);
					return "Service.List";
				}else {
					errors.put("SelectResult", "查無資料");
					return "Service.List";
				}
			}
			return "Service.errors";

		} if ("提交".equals(servicemethod)) {
			// bean.setAsktime(new java.util.Date());
			System.out.println(bean);
			ServiceBean result = serviceService.insert(bean);

			if (result == null) {

				System.out.println("false");
				errors.put("title", "Insert fail");
				return "Service.errors";

			} else {
				System.out.println("succest");
				List<ServiceBean> insertresult = serviceService.select(bean);
				model.addAttribute("select", insertresult);
				return "Service.List";
			}
		} else if ("更新".equals(servicemethod)) {
			// bean.setAsktime(new java.util.Date());
			System.out.println(bean);
			bean.setAsktime(new java.util.Date());
			ServiceBean result = serviceService.update(bean);
			List<ServiceBean> insertresult = serviceService.select(bean);
			model.addAttribute("select", insertresult);
			return "Service.List";
		}
		errors.put("action", "Unknown Action:" + servicemethod);
		return "Service.errors";
	}

	@RequestMapping(
				path= {"/ServiceSelect.controller"}
			)
	public String selectMethod(Model model,ServiceBean bean) {
		List<ServiceBean> result = serviceService.select(bean);
		model.addAttribute("select", result);
		return "Service.List";
	}
}
