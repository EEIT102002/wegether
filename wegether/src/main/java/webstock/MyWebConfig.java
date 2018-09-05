package webstock;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import webstock.model.MyHandler;

@Configuration
@EnableWebSocket
@EnableWebMvc
public class MyWebConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(new MyHandler(), "/myHandler")
              .setAllowedOrigins("*");
  }
}