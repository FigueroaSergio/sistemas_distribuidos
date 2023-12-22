 /*
 *  wsocketClient.java
 *
 *  @author Jose Simo. (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 *  
 */
package wsocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class wsocketClient {

    public static void main(String[] args) {
        try {
            // open websocket
        	final URI endPointURI = new URI("ws://localhost:8080/se12_sockets/sensor");
            final wsocketClientEndpoint clientEndPoint = new wsocketClientEndpoint(endPointURI);

            for (;;) {
	            // send message to websocket to start the messages flow
	            clientEndPoint.sendMessage("{\"start\":\"true\"}");
	            // wait 5 seconds for messages from websocket
	            Thread.sleep(5000);
	            // send message to websocket to stop the messages flow
	            clientEndPoint.sendMessage("{\"stop\":\"true\"}");
	            Thread.sleep(5000);
            }

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
