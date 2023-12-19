 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package client;

import java.net.URI;

import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.ClientBuilder;

public class ClientRestSensors {

	private static UriBuilder getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/se11_sensors");
	}
	
	public static void main(String[] args) {  
		
	    Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(getBaseURI().path("rest").path("sensors").build());
		String result = target.request("application/xml").get(String.class); 
		System.out.println("All sensors:\n"+result);
		
		String sensorId = "T00001";
		URI resourceUri = getBaseURI().path("rest").path("sensors").path(sensorId).path("wait").build();
	    target = client.target(resourceUri).queryParam("delay", "500");
		while (true) {
			String result1 = target.request("application/json").get(String.class); 
			System.out.println("Sensor: "+result1);	
		}
	}	
}
