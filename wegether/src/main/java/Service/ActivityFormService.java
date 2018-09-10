package Service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class ActivityFormService {
	
	@SuppressWarnings({ "unchecked" })
	public String checkActivityForm(String form , Map<String, String> data, Map<String, String> errors) {
		JSONArray qs = null;
		try {
			qs = (JSONArray)(new JSONParser().parse(form));
		} catch (ParseException e) {
			return null;
		}
		
		JSONArray answers = new JSONArray();
		for(Object row : qs){
			JSONObject jrow = (JSONObject)row;
			JSONObject answer = new JSONObject();
			String title = (String)jrow.get("title");
			String name = (String)jrow.get("name");
			String type = (String)jrow.get("type");
			boolean required = (boolean)jrow.get("required");
			switch(type){
				case "checkbox":{
					JSONArray checkbox = new JSONArray();
					JSONObject options = (JSONObject)jrow.get("options");
					options.forEach((n, v) -> {
						if(data.get((String)n) != null)
							checkbox.add(v);
					});					
					answer.put("title", title);
					answer.put("answer", checkbox);
					if(required && checkbox.size() <= 0) {
						errors.put(name, "必填欄位");
					}
					break;
				}
				case "radio":
				case "select":{
					JSONObject options = (JSONObject)jrow.get("options");
					String value = (String)options.get(data.get(name));
					answer.put("title", title);
					answer.put("answer", value);
					if(required && value == null) {
						errors.put(name, "必填欄位");
					}
					break;
				}
				default:
					String value = data.get(name);
					answer.put("title", title);
					answer.put("answer", value);
					if(required && (value == null || value.trim().length() == 0)) {
						errors.put(name, "必填欄位");
					}
					break;
			}
			answers.add(answer);

		}
		return answers.toString();
	}

}
