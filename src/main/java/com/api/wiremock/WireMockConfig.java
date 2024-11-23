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
    /**
     * Starts the WireMock server with a dynamically assigned port and a console notifier.
     *
     * @see WireMockServer
     * @see ConsoleNotifier
     */
    public static void startServer(){
            server = new WireMockServer
                    (options().dynamicPort()
                    .notifier(new ConsoleNotifier(true)));
            server.start();
            port = server.port();
    }
    /**
     * Sets up a stub for GET requests to the Users endpoint, returning a predefined JSON response with a status code of 200.
     */
    public static void getUsers()
   {
        server.stubFor(get(urlEqualTo(APIConstants.WIREMOCK_USERS_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_users.json")
                        .withStatus(200)));

    }
     //Sets up a stub for GET requests to the Posts endpoint, returning a predefined JSON response with a status code of 200.
    public static void getPosts()
    {
        server.stubFor(get(urlEqualTo(APIConstants.WIREMOCK_POSTS_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withBodyFile("GET_posts.json")
                        .withStatus(200)));

    }
    //Stops the wiremock server
    public static void stopServer(WireMockServer server) {
        server.stop();
    }

}
