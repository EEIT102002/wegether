package webstock.model;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Repository
public class MyHandler extends TextWebSocketHandler {
	@Autowired
	List<WebSocketSession> websocketList;
	
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	  websocketList.add(session);
	  System.out.println(websocketList.size());
	  websocketList.forEach(x->{
		try{
			x.sendMessage(new TextMessage("echo123: "+message.getPayload()));
		}catch(IOException e){
			e.printStackTrace();
		}
	});
//      session.sendMessage(new TextMessage("echo123: "+message.getPayload()));
  }
}