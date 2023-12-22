package client;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.client.ClientBuilder;

public class ClientRestItems {

	
	private static UriBuilder getBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/prj-restItems/rest");
	}
	
	public static void main(String[] args) {  
		
	    Client client = ClientBuilder.newClient();
	    
	    //List all items
		WebTarget target =  client.target(getBaseURI().path("items").build());
		//(GET) XML Request
		String result = target.request("application/xml").get(String.class); 
		System.out.println("Result: "+result);
		
		//EJERCICIO: Listar todos los items en JSON.	
		
		String itemId ="REF00001";
		long amount = 2;
		Entity<String> entity = Entity.entity(""+amount,MediaType.TEXT_PLAIN_TYPE);
		
		//EJERCICIO: Decrementar la cantidad del item "itemId" 
		//           en una cantidad "amount" (llamada PUT enviando "entity").
		
		
		//EJERCICIO: Obtener y mostrar en pantalla una representación del item "itemId" para 
		//           comprobar que efectivamente se ha decrementado la cantidad. (GET)
		
	}
}
