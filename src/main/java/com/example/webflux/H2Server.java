package com.example.webflux;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import static org.h2.tools.Server.createWebServer;

@Component
public class H2Server {


    private Server webServer;

    @Value("${h2-server.port}")
    Integer h2ConsolePort;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {

        this.webServer = createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers").start();
        System.out.println(webServer.getURL());
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {

        this.webServer.stop();
    }

}
