package servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson{
	
	String fileurl = "C:\\DataSource\\webspace\\JspProject2\\WebContent\\res\\formJson.txt";
	JSONArray questions;
	
	public static void main(String[] args){
		ReadJson rj = new ReadJson();
		JSONArray qs = rj.getJSON();
		
		JSONObject answer = new JSONObject();
		for(Object row :qs) {
			JSONObject jrow = (JSONObject)row;
			String title = (String)jrow.get("title");
			String name = (String)jrow.get("name");
			String type = (String)jrow.get("type");
			System.out.println(title);
			if("checkbox".equals(type)) {
				JSONObject options = (JSONObject)jrow.get("options");
				JSONArray checkbox = new JSONArray();
				options.forEach((n,v)->{
					checkbox.add(v);
				});
				answer.put(title, checkbox);
			}else {
				answer.put(title, name);
			}
		}
		
		System.out.println(answer.toString());
	}
	
	public JSONArray getJSON(){
		JSONObject form;
		try{
			form = ((JSONObject)new JSONParser().parse(new FileReader(fileurl)));
			questions = (JSONArray)form.get("questions");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		return questions;
	}

}
