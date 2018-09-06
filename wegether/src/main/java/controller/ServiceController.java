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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import Service.ServiceService;
import model.ServiceBean;
import model.dao.ServiceDAO;



@Controller
@SessionAttributes(names= {"select","action"})
public class ServiceController {
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private ServiceDAO serviceDAO;
	
	@InitBinder
	public void registerPropertyEditor(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

//		 webDataBinder.registerCustomEditor(double.class, "price",
//		 new PrimitiveNumberEditor(Double.class, true));
//		
//		 webDataBinder.registerCustomEditor(int.class,
//		 new PrimitiveNumberEditor(Integer.class, true));
		
//		 webDataBinder.registerCustomEditor(double.class,
//		 new PrimitiveNumberEditor(Double.class, true));
//		
//		 webDataBinder.registerCustomEditor(int.class,
//		 new PrimitiveNumberEditor(Integer.class, true));
	}

	@RequestMapping(path = { "/Service.controller" })
	public String method(Model model, String servicemethod, ServiceBean bean, BindingResult bindingResult,
			@RequestParam("memberid") String temp1) {
		System.out.println("Service.controller");

		// 接收資料
		// 轉換資料
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errors", errors);

		if (bindingResult != null && bindingResult.hasFieldErrors()) {
			if (bindingResult.hasFieldErrors("memberid")) {
				errors.put("memberid", "memberid must be an integer");
			}
			
			if (bindingResult.hasFieldErrors("title")) {
				errors.put("title", "title must write");
			}

			if (bindingResult.hasFieldErrors("classtype")) {
				errors.put("classtype", "classtype must select");
			}

			if (bindingResult.hasFieldErrors("Content")) {
				errors.put("Content", "Content must write");
			}
		}

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
//			List<ServiceBean> result = serviceDAO.select();
			System.out.println(result.size());
			model.addAttribute("select", result);
			return "Service.List";
		} else

		if ("提交".equals(servicemethod)) {
			bean.setAsktime(new java.util.Date());
			System.out.println(bean);

//			ServiceBean result = serviceDAOHibernate.insert(bean);
//			if (result == null) {
//				
//				System.out.println("false");
//				errors.put("action", "Insert fail");
//				return "Service.insertpage";
//				
//			} else {
//				System.out.println("succest");
//				List<ServiceBean> insertresult = serviceDAOHibernate.select();
//				model.addAttribute("select", insertresult);
//				return "Service.List";
//			}
			
		
		}
		errors.put("action", "Unknown Action:" + servicemethod);
		return "Service.errors";

		// 根據Model執行結果呼叫View
		// if("Select".equals(prodaction)) {
		// List<ProductBean> result = productService.select(bean);
		// model.addAttribute("select", result);
		// return "product.servlet";
		// } else if("Insert".equals(prodaction)) {
		// ProductBean result = productService.insert(bean);
		// if(result==null) {
		// errors.put("action", "Insert fail");
		// } else {
		// model.addAttribute("insert", result);
		// }
		// return "product.error";
		// } else if("Update".equals(prodaction)) {
		// ProductBean result = productService.update(bean);
		// if(result==null) {
		// errors.put("action", "Update fail");
		// } else {
		// model.addAttribute("update", result);
		// }
		// return "product.error";
		// } else if("Delete".equals(prodaction)) {
		// boolean result = productService.delete(bean);
		// if(!result) {
		// model.addAttribute("delete", 0);
		// } else {
		// model.addAttribute("delete", 1);
		// }
		// return "product.error";
		// }
		// else {
		// errors.put("action", "Unknown Action:"+servicemethod);
		// return "product.error";
		// }
		
//		 */
//		return "Service.errors";
	}

		
		
}
