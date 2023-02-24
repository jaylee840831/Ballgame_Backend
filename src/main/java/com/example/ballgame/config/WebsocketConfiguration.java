package com.example.ballgame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//指定映射註冊為stomp協定的url為"/server1"，同時指定使用sockJS協定
//		registry.addEndpoint("/server1").setAllowedOriginPatterns("*").withSockJS();
		registry.addEndpoint("/server1").setAllowedOriginPatterns("http://localhost:4200").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//實現訊息推送功能，指定消息代理為"/topic"
		registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
	}
}
