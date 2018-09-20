package controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.ArticleBean;
import model.PictureBean;
import model.dao.ArticleDAO;
import model.dao.PictureDAO;

@Controller
public class ArticleCreateController2 {
	@Autowired
	ArticleDAO articleDAO;

	@Autowired
	PictureDAO pictureDAO;

	@Autowired
	ApplicationContext context;

	@RequestMapping(path = { "/articleCreate.controller2" }, method = RequestMethod.POST)
	public String artCreate(Model model,
			@RequestParam(value = "files[]", required = false) MultipartFile[] files) throws IOException {
		System.out.println("articleCreate()");

//		if (content.isEmpty()) {
//			model.addAttribute("content", "請輸入內容");
//			return "artCreSuc.page";
//		}

		ArticleBean articleBean = (ArticleBean) context.getBean("articleBean");

		articleBean.setMemberid(4); // 改
		articleBean.setActivityid(78); // 改
		articleBean.setContent("i like it");
		articleDAO.insert(articleBean);
		System.out.println("1");

		int articleId = articleDAO.getArticleId(4, 78); // 改
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
		return "artCreSuc.page";
	}
}