package webstock.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import Service.bean.LoginBean;


@Repository
public class NoticeHandler extends TextWebSocketHandler {
	@Autowired
	Map<Integer, LoginBean> loginMap;
	@Autowired
	Map<WebSocketSession, Integer> sessionMap;
	@Resource(name = "noticeTokenMap")
	private Map<String, Integer> noticeTokenMap;
	
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message.getPayload());
		TextMessage textmessage = new TextMessage("echo: " + message.getPayload());
//		session.sendMessage(new TextMessage("echo: " + message.getPayload()));
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception {
		Integer id = sessionMap.get(session);
		loginMap.get(id).getSessions().remove(session);
		sessionMap.remove(session);
		session.close();
		System.out.println("loginMap("+id+") :"+loginMap.get(id).getSessions().size());
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String token= session.getUri().getQuery().substring(6);
		Integer id = noticeTokenMap.get(token);
		if(id != null) {
			loginMap.get(id).getSessions().add(session);
			noticeTokenMap.remove(token);
			sessionMap.put(session, id);
			session.sendMessage(new TextMessage("login"));
		}
		System.out.println("loginMap:"+loginMap.get(id).getSessions().size());

	}

	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}
