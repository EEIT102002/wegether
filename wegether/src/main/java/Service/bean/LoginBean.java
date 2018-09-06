package Service.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@Scope("prototype")
public class LoginBean {
	private List<WebSocketSession> sessions = new ArrayList<>();;
	private List<String> tokens= new ArrayList<>();
	private Integer id;
	public LoginBean(Integer id) {
		this.id = id;
	}
	public List<WebSocketSession> getSessions() {
		return sessions;
	}
	public void setSessions(List<WebSocketSession> sessions) {
		this.sessions = sessions;
	}
	public List<String> getTokens() {
		return tokens;
	}
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	
}
