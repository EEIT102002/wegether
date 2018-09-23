package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import model.ArticleBean;
import model.PictureBean;
import model.dao.ArticleDAO;
import model.dao.PictureDAO;
import pictureconvert.PictureConvert;

@Controller
public class ArticleEditController {
	@Autowired
	ArticleDAO articleDAO;

	@Autowired
	PictureDAO pictureDAO;

	@Autowired
	ApplicationContext context;

	@RequestMapping("/artEdit.getBean.controller")
	public String artCreate(Model model, Integer artid,
			@RequestAttribute(value = "memberid", required = false) Integer id) {
		System.out.println("articleEditGetBean()");
		System.out.println("artid=" + artid);

		if (id == null) {
			model.addAttribute("loginFail", "請登入");
			return "index.success";
		}

		model.addAttribute("artid", artid);

		ArticleBean articleResult = articleDAO.select(artid); // 改
		model.addAttribute("articleResult", articleResult);

		List<String> articlePics = new ArrayList<>();
		List<Integer> picIds = new ArrayList<>();
		List<PictureBean> pictureResult = pictureDAO.selectByArticle(artid); // 改

		int len = pictureResult.size();
		if (len == 0) {
		} else {
			for (int i = 0; i < len; i++) {
				PictureBean oneBean = pictureResult.get(i);
				String picture = PictureConvert.convertBase64Image(oneBean.getPicture());
				articlePics.add(picture);
				model.addAttribute("articlePics", articlePics);

				Integer oneId = oneBean.getId();
				picIds.add(oneId);
				model.addAttribute("picIds", picIds);
			}
		}
		return "artEdit.page";
	}

	@RequestMapping("/insertArtOtherPics.do")
	@ResponseBody
	public PictureBean create(@RequestParam(value = "multipicture", required = false) MultipartFile[] picture,
			int articleId) throws URISyntaxException, IOException {
		PictureBean result;
		for (int i = 0; i < picture.length; i++) {
			PictureBean pictureBean = (PictureBean) context.getBean("pictureBean");
			picture[i].getBytes();
			pictureBean.setArticleid(articleId);
			pictureBean.setPicture(picture[i].getBytes());
			result = pictureDAO.insert(pictureBean);
			return result;
		}
		return null;
	}

	@RequestMapping(path = { "/artEdit.edit.controller" }, method = RequestMethod.POST)
	public String artEdit(Model model, @RequestParam(required = false) String content,
			@RequestParam(required = false) String artid, HttpServletRequest request,
			@RequestAttribute(value = "memberid", required = false) Integer id) throws IOException {
		System.out.println("articleEdit()");

		if (id == null) {
			model.addAttribute("loginFail", "請登入");
			return "index.success";
		}

		if (content == null || content.isEmpty()) {
			model.addAttribute("content", "請輸入內容");
			return "artCreSuc.page";
		}

		int artid2 = Integer.parseInt(artid);
		boolean updateResult = articleDAO.update(artid2, content);

		if (updateResult == false)
			return "artCreSuc.page";

		return "artCreSuc.page";
	}
}