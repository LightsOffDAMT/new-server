package com.company.server.util;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.ClientOperations;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;


public class ServerConfigurationBuilder {
    private final Configuration configuration = new Configuration();



    public class EventConfigurationBuilder {
        private final SocketIOServer server = new SocketIOServer(configuration);
        private final HashMap<String, Pair<ArrayList<DataListener>, Class<?>>> eventListener = new HashMap<>();



        public class ListenerConfigurationBuilder{
            String event;
            Class<?> aClass;
            ArrayList<DataListener> listeners = new ArrayList<>();


            private ListenerConfigurationBuilder(final String event, final Class<?> dataClass){
                this.event = event;
                this.aClass = dataClass;
            }

            public ListenerConfigurationBuilder with(final DataListener listener){
                listeners.add(listener);
                return ListenerConfigurationBuilder.this;
            }

            public EventConfigurationBuilder and(){
                eventListener.put(event, Pair.of(listeners, aClass));
                return EventConfigurationBuilder.this;
            }
        }



        public ListenerConfigurationBuilder listen(final String eventName, final Class<?> dataClass){
            return new ListenerConfigurationBuilder(eventName, dataClass);
        }

        public EventConfigurationBuilder onConnect(ConnectListener connectListener){
            server.addConnectListener(connectListener);
            return EventConfigurationBuilder.this;
        }

        public EventConfigurationBuilder onDisconnect(DisconnectListener disconnectListener){
            server.addDisconnectListener(disconnectListener);
            return EventConfigurationBuilder.this;
        }

        public SocketIOServer build(){
            for (final String eventName : eventListener.keySet()) {
                final Pair<ArrayList<DataListener>, Class<?>> toInsert = eventListener.get(eventName);
                for (final DataListener dataListener : toInsert.getLeft())
                    server.addEventListener(eventName, toInsert.getRight(), dataListener);
            }
            return server;
        }
    }



    public ServerConfigurationBuilder hostname(final String hostname){
        configuration.setHostname(hostname);
        return this;
    }

    public ServerConfigurationBuilder port(final int port){
        configuration.setPort(port);
        return this;
    }

    public ServerConfigurationBuilder authorizationListener(final AuthorizationListener listener){
        configuration.setAuthorizationListener(listener);
        return this;
    }

    public EventConfigurationBuilder buildServer(){
        return new EventConfigurationBuilder();
    }
}
