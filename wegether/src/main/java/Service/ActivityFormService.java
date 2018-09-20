package Service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ActivityBean;
import model.dao.ActivityDAO;

@Service
public class ActivityFormService {
	@Autowired
	ActivityDAO activityDAO;
	@Autowired
	private JSONParser jsonParser;

	@SuppressWarnings({ "unchecked" })
	public String checkActivityForm(Integer id, Map<String, String> data, Map<String, String> errors) {
		JSONObject form = getApplyForm(id);
		if (form == null) {
			return null;
		}
		Boolean hasForm = (Boolean) form.get("hasForm");
		if(!hasForm) {
			return "default";
		}	
		JSONArray qs = (JSONArray) (form.get("questions"));
		JSONArray answers = new JSONArray();
		for (Object row : qs) {
			JSONObject jrow = (JSONObject) row;
			JSONObject answer = new JSONObject();
			String title = (String) jrow.get("title");
			String name = (String) jrow.get("name");
			String type = (String) jrow.get("type");
			boolean required = (boolean) jrow.get("required");
			switch (type) {
			case "checkbox": {
				JSONArray checkbox = new JSONArray();
				JSONArray options = (JSONArray) jrow.get("options");
				options.forEach((n) -> {
					JSONObject option = (JSONObject) n;
					option.forEach((k, v) -> {
						if (data.get((String) k) != null)
							checkbox.add(v);
					});
				});
				answer.put("title", title);
				answer.put("answer", checkbox);
				if (required && checkbox.size() <= 0) {
					errors.put(name, "必填欄位");
				}
				break;
			}
			case "radio":
			case "select": {
				JSONArray options = (JSONArray) jrow.get("options");
				answer.put("title", title);
				options.forEach((n) -> {
					Object value = ((JSONObject) n).get(data.get(name));
					if (value != null) {
						answer.put("answer", (String) value);
					}
				});

				if (required && answer.get("answer") == null) {
					errors.put(name, "必填欄位");
				}
				break;
			}
			default:
				String value = data.get(name);
				answer.put("title", title);
				answer.put("answer", value);
				if (required && (value == null || value.trim().length() == 0)) {
					errors.put(name, "必填欄位");
				}
				break;
			}
			answers.add(answer);

		}
		return answers.toString();
	}

	public JSONObject getApplyForm(Integer id) {
		ActivityBean bean = activityDAO.selectId(id);
		if (bean != null) {
			try {
				return (JSONObject) (jsonParser.parse(bean.getForm()));
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	public JSONObject stringToJsonObject(String str) {
		try {
			return (JSONObject) (jsonParser.parse(str));
		} catch (ParseException e) {
			return null;
		}
	}

}
