 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package client;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;

public class ClientRest {

	private static UriBuilder getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/prj-restHello");
	}
	
	public static void main(String[] args) {  
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(getBaseURI().path("rest").path("hello").build());
		
		String result = target.request(MediaType.TEXT_PLAIN).get(String.class); 
		System.out.println("Result 1: "+result);
		
		result = target.request(MediaType.TEXT_XML).get(String.class); 
		System.out.println("Result 2: "+result);
		
		result = target.request(MediaType.TEXT_HTML).get(String.class); 
		System.out.println("Result 3: "+result);	
		
		WebTarget target2 = client.target(getBaseURI().path("rest").path("hello").path("maspath").build());
		Response resp = target2.request(MediaType.TEXT_HTML).post(Entity.text("Texto cualquiera"));
		System.out.println("Result 4: "+resp.readEntity(String.class));	
	}
}

