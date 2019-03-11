package com.company;

import com.company.game.mechanics.GameCycle;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Bootstrap {
    final GameCycle gameCycle;
    final SocketIOServer server;

    @Autowired
    public Bootstrap(GameCycle gameCycle, final SocketIOServer server) {
        this.gameCycle = gameCycle;
        this.server = server;
    }

    void init(){
        ScheduledExecutorService es = Executors.newScheduledThreadPool(8);
        es.scheduleAtFixedRate(gameCycle, 5000, 1000, TimeUnit.MILLISECONDS);
        server.start();
    }
}
