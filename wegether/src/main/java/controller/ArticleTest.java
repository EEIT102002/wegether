package controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.ArticleBean;
import model.ArticleDao;

@Controller
public class ArticleTest {
	@Autowired
	private ArticleDao articleDao;

	@RequestMapping("/test.do2")
	public String test(@RequestParam("id") int id, Model model) {
		ArticleBean result = articleDao.select(id);
		model.addAttribute("articleid", 2);
		model.addAttribute("memberid", result.getMemberid());
		System.out.println("mid="+result.getMemberid());
		return "oh";
	}
}
