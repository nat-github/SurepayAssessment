package com.api.wiremock;

import com.api.config.APIConstants;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockConfig {
    public static WireMockServer server;
    public static int port;
    public static void startServer(){
            server = new WireMockServer
                    (options().dynamicPort()
                    .notifier(new ConsoleNotifier(true)));
            server.start();
            port = server.port();
    }
    public static void getUsers()
   {
        server.stubFor(get(urlEqualTo(APIConstants.WIREMOCK_USERS_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_users.json")
                        .withStatus(200)));

    }
    public static void getPosts()
    {
        server.stubFor(get(urlEqualTo(APIConstants.WIREMOCK_POSTS_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_posts.json")
                        .withStatus(200)));

    }
    public static void stopServer(WireMockServer server) {
        server.stop();
    }

}
