package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.ArticleBean;
import model.PictureBean;
import model.dao.ArticleDAO;
import model.dao.PictureDAO;

@Controller
public class ArticleCreateController {
	@Autowired
	ArticleDAO articleDAO;

	@Autowired
	PictureDAO pictureDAO;

	@Autowired
	ApplicationContext context;

	@RequestMapping(path = { "/articleCreate.controller" }, method = RequestMethod.POST)
	public String artCreate(Model model, @RequestParam(required = false) String content,
			@RequestParam(value = "multipicture", required = false) MultipartFile[] files,
			@RequestParam(required = false) String actid, @RequestParam(required = false) String actname,
			HttpServletRequest request, @RequestAttribute(value = "memberid", required = false) Integer id)
			throws IOException {
		System.out.println("articleCreate()");

		if (id == null) {
			model.addAttribute("loginFail", "請登入");
			return "index.success";
		}

		if (content.isEmpty()) {
			model.addAttribute("content", "請輸入內容");
			return "artCreSuc.page";
		}

		model.addAttribute("actname", actname);

		ArticleBean articleBean = (ArticleBean) context.getBean("articleBean");

		int actid2 = Integer.parseInt(actid);

		articleBean.setMemberid(id);
		articleBean.setActivityid(actid2); // 改
		articleBean.setContent(content);
		articleDAO.insert(articleBean);
		System.out.println("1");

		int articleId = articleDAO.getArticleId(id, actid2); // 改
		System.out.println("articleId = " + articleId);

		System.out.println("files.length = " + files.length);
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				PictureBean pictureBean = (PictureBean) context.getBean("pictureBean");
				pictureBean.setArticleid(articleId);
				byte[] pic = files[i].getBytes();
				pictureBean.setPicture(pic);
				pictureDAO.insert(pictureBean);
			}
		}
		request.setAttribute("id", articleBean.getId());
		request.setAttribute("ntype", 10);
		return "artCreSuc.page";
	}
}