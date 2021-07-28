package nl.altindag.ssl.ws;

import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.CertificateUtils;
import nl.altindag.ssl.util.JettySslUtils;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws Exception {
        var sslFactory = SSLFactory.builder()
                .withTrustMaterial(CertificateUtils.loadCertificate("isrg-root-x1.pem"))
                .build();

        var sslContextFactory = JettySslUtils.forClient(sslFactory);
        var httpClient = new HttpClient(sslContextFactory);
        var webSocketClient = new WebSocketClient(httpClient);
        webSocketClient.start();

        MyWebSocketListener webSocketListener = new MyWebSocketListener();
        var session = webSocketClient.connect(webSocketListener, new URI("ws://echo.websocket.org")).get();
        session.getRemote().sendString("Hello there!");

        // waiting till the server response before closing the client
        int counter = 0;
        while (!webSocketListener.hasReceivedServerResponse() && counter <= 20) {
            counter++;
            TimeUnit.MILLISECONDS.sleep(100);
        }

        webSocketClient.stop();
        httpClient.stop();
    }

    private static class MyWebSocketListener implements WebSocketListener {

        private boolean hasReceivedServerResponse = false;

        @Override
        public void onWebSocketBinary(byte[] bytes, int i, int i1) {
            System.out.println();
        }

        @Override
        public void onWebSocketText(String response) {
            hasReceivedServerResponse = true;
            System.out.println("Received the following message from the server: " + response);
        }

        @Override
        public void onWebSocketClose(int i, String s) {
            System.out.println("closed");
        }

        @Override
        public void onWebSocketConnect(Session session) {
            System.out.println("connected");
        }

        @Override
        public void onWebSocketError(Throwable throwable) {
            System.out.println("got error");
        }

        public boolean hasReceivedServerResponse() {
            return hasReceivedServerResponse;
        }
    }

}
