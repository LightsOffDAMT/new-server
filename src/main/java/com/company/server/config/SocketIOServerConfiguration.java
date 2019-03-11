package com.company.server.config;

import com.company.server.eventHandlers.MovePlayerIncomingHandler;
import com.company.server.eventHandlers.helpers.PlayerResolverIncomingHandler;
import com.company.server.users.SocketIOClientUserService;
import com.company.server.users.UserService;
import com.company.server.util.EventPipelineConfigurationBuilder;
import com.company.server.util.ServerConfigurationBuilder;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class SocketIOServerConfiguration {
    @Value("${server.address}")
    String hostname;

    @Value("${server.port}")
    int port;

    @Autowired
    PlayerResolverIncomingHandler playerResolverIncomingHandler;
    @Autowired
    MovePlayerIncomingHandler movePlayerIncomingHandler;


    @Bean
    public DataListener moveEventListener(){
        return new EventPipelineConfigurationBuilder()
                .next(playerResolverIncomingHandler)
                .next(movePlayerIncomingHandler)
                .build();
    }


    @Bean
    public UserService<SocketIOClient> userService(){
        return new SocketIOClientUserService();
    }

    @Bean
    SocketIOServer server(){
        return new ServerConfigurationBuilder()
                .hostname(hostname)
                .port(port)
                .buildServer()
                .listen("SHOOT", String.class)
                    .with(moveEventListener())
                .and()
                .build();
    }
}
