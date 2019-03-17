package com.company.server.config;

import com.company.game.mechanics.handlers.fire.StatelessFireEventIncomingHandler;
import com.company.game.mechanics.handlers.move.MoveEventDeserializerIncomingHandler;
import com.company.game.mechanics.handlers.move.MoveEventResolverIncomingHandler;
import com.company.game.mechanics.handlers.move.MovePlayerEventIncomingHandler;
import com.company.game.mechanics.handlers.util.AvailabilityCheckEventIncomingHandler;
import com.company.game.mechanics.handlers.util.IdentifyingEventIncomingHandler;
import com.company.game.mechanics.handlers.util.ProxyLoggerEventIncomingHandler;
import com.company.server.users.SocketIOClientUserService;
import com.company.server.users.UserService;
import com.company.server.util.EventPipelineConfigurationBuilder;
import com.company.server.util.Payload;
import com.company.server.util.ServerConfigurationBuilder;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


@org.springframework.context.annotation.Configuration
public class SocketIOServerConfiguration {
    @Value("${server.address}")
    String hostname;

    @Value("${server.port}")
    int port;

    @Bean
    @Scope("prototype")
    AvailabilityCheckEventIncomingHandler availabilityCheckEventIncomingHandler(){
        return new AvailabilityCheckEventIncomingHandler();
    }
    @Bean
    @Scope("prototype")
    IdentifyingEventIncomingHandler identifyingEventIncomingHandler(){
        return new IdentifyingEventIncomingHandler();
    }
    @Bean
    @Scope("prototype")
    MoveEventResolverIncomingHandler moveEventResolverIncomingHandler(){
        return new MoveEventResolverIncomingHandler();
    }
    @Bean
    @Scope("prototype")
    MoveEventDeserializerIncomingHandler moveEventDeserializerIncomingHandler(){
        return new MoveEventDeserializerIncomingHandler();
    }
    @Bean
    @Scope("prototype")
    ProxyLoggerEventIncomingHandler proxyLoggerEventIncomingHandler(){
        return new ProxyLoggerEventIncomingHandler();
    }
    @Bean
    @Scope("prototype")
    MovePlayerEventIncomingHandler movePlayerEventIncomingHandler(){
        return new MovePlayerEventIncomingHandler();
    }
    @Bean
    @Scope("prototype")
    StatelessFireEventIncomingHandler statelessFireEventIncomingHandler(){
        return new StatelessFireEventIncomingHandler();
    }
    @Autowired
    ConnectListener baseConnectListener;
    @Autowired
    DisconnectListener disconnectListener;

    @Bean
    public UserService<SocketIOClient> userService(){
        return new SocketIOClientUserService();
    }

    @Bean
    DataListener moveEventListener(){
        return new EventPipelineConfigurationBuilder()
                .next(identifyingEventIncomingHandler())
                //.next(availabilityCheckEventIncomingHandler)
                .next(moveEventDeserializerIncomingHandler())
                .next(moveEventResolverIncomingHandler())
                .next(proxyLoggerEventIncomingHandler())
                .next(movePlayerEventIncomingHandler())
                .build();
    }

    @Bean
    DataListener fireEventListener(){
        return new EventPipelineConfigurationBuilder()
                .next(identifyingEventIncomingHandler())
                .next(proxyLoggerEventIncomingHandler())
                .next(statelessFireEventIncomingHandler())
                .build();
    }

    @Bean
    SocketIOServer server(){
        return new ServerConfigurationBuilder()
                .hostname(hostname)
                .port(port)
                .buildServer()
                .onConnect(baseConnectListener)
                .onDisconnect(disconnectListener)
                .listen("MoveEvent", Payload.class)
                    .with(moveEventListener())
                .and()
                .listen("FireEvent", Payload.class)
                    .with(fireEventListener())
                .and()
                .build();
    }
}
