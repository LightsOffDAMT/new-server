package com.company.game.config;

import com.company.game.contributed.GameStorage;
import com.company.game.contributed.PlayerGameStorage;
import com.company.game.mechanics.GameCycle;
import com.company.server.broadcast.ResponseService;
import com.company.server.broadcast.SocketIOBasedResponseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GameConfiguration {
    @Bean
    public GameStorage players(){
        return new PlayerGameStorage();
    }

    @Bean
    public ResponseService responseService(){
        return new SocketIOBasedResponseService();
    }

    @Bean
    public GameCycle gameCycle(){
        return new GameCycle();
    }
}
