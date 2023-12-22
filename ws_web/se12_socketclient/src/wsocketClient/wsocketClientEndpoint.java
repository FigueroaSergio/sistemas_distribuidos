 /*
 *  wsocketClientEndPoint.java
 *
 *  @author Jose Simo. (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 *  
 */
package wsocketClient;

import java.net.URI;
import java.nio.ByteBuffer;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

/**
 * websocket Client
 */
@ClientEndpoint
public class wsocketClientEndpoint {

    Session userSession = null;

    public wsocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Callback hook for Connection open events.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     */
    @OnClose
    public void onClose(Session userSession) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     */
    @OnMessage
    public void onMessage(String message) {
    	
    	System.out.println(message);
    }

   @OnMessage
   public void onMessage(ByteBuffer bytes) {
        System.out.println("Handle byte buffer");
    }

    /**
     * Send a string message.
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }
}



