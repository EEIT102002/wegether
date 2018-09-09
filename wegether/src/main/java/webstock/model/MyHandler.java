package webstock.model;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Repository
public class MyHandler extends TextWebSocketHandler {
	
	@Autowired
	List<WebSocketSession> websocketList;
	
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message.getPayload());
		TextMessage textmessage = new TextMessage("echo: " + message.getPayload());
		websocketList.forEach(x-> 
			{	try {
					x.sendMessage(textmessage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
//		session.sendMessage(new TextMessage("echo: " + message.getPayload()));
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception {
		websocketList.remove(session);
		System.out.println(websocketList.size());
			
	}

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		websocketList.add(session);
		System.out.println(websocketList.size());

	}

	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}