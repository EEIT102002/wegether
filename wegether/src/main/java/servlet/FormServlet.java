package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class formServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processform(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request, response);
	}

	@SuppressWarnings("unchecked")
	private void processform(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		JSONArray qs = new ReadJson().getJSON();

//		JSONObject answer = new JSONObject();
		JSONArray answers = new JSONArray();
		for(Object row : qs){
			JSONObject jrow = (JSONObject)row;
			JSONObject answer = new JSONObject();
			String title = (String)jrow.get("title");
			String name = (String)jrow.get("name");
			String type = (String)jrow.get("type");
			switch(type){
				case "checkbox":{
					JSONArray checkbox = new JSONArray();
					JSONObject options = (JSONObject)jrow.get("options");
					options.forEach((n, v) -> {
						if(request.getParameter((String)n) != null)
							checkbox.add(v);
					});
					answer.put("title", title);
					answer.put("answer", checkbox);
					break;
				}
				case "radio":
				case "select":{
					JSONObject options = (JSONObject)jrow.get("options");
					String value = (String)options.get(request.getParameter(name));
					answer.put("title", title);
					answer.put("answer", value);
					break;
				}
				default:
					String value = request.getParameter(name);
					answer.put("title", title);
					answer.put("answer", value);
					break;
			}
			answers.add(answer);

		}
		out.println(answers.toJSONString());

		out.close();
	}
}
