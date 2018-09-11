package webstock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import webstock.model.MyHandler;
import webstock.model.NoticeHandler;


@Configuration
@EnableWebSocket
@EnableWebMvc
public class NoticeWebConfig implements WebSocketConfigurer {
	@Autowired
	NoticeHandler noticeHandler;
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(noticeHandler, "/noticeHandler").setAllowedOrigins("*");
	}

}
