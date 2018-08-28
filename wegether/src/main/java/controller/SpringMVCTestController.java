package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringMVCTestController {
	
	
	@RequestMapping("/test.do")
	public String processTest(Model model) {
		model.addAttribute("test","success");
		return "test.success";
	}

}
