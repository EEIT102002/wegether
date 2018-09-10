package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Service.ActivityFormService;
import servlet.ReadJson;

@Controller
public class ActivityFormController {
	@Autowired
	private ActivityFormService service;
	
	@RequestMapping(path = { "/FormServletTest" })
	public void getApply(@RequestParam Map<String, String> data,Model model, HttpServletResponse response) throws ParseException, IOException {
		JSONArray qs = new ReadJson().getJSON();
		Map<String, String> errors = new HashMap<String, String>();
		String answers = service.checkActivityForm(qs.toJSONString(), data, errors);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(answers);

		out.close();
	}

}
