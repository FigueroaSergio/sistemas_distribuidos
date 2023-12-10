 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class Hello  {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String plainTextHello() {
    return "Hello SDI. Plain text.";
  }
//At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.

  @GET
  @Produces(MediaType.TEXT_XML)
  public String xmlHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello SDI. XML." + "</hello>";
  }
  
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String htmlHello() {
    return "<html> " + "<title>" + "Hello SDI" + "</title>"
        + "<body><h1>" + "Hello SDI. HTML." + "</body></h1>" + "</html> ";
  }
  
  @POST
  @Path("{ruta}")
  public Response postMsg(@PathParam("ruta") String miruta, @Context HttpServletRequest req) {

	  BufferedReader rea = null;
	  String mm = "";
	try {
		rea = new BufferedReader(new InputStreamReader(req.getInputStream()));
		mm = rea.readLine();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	String output = "Respuesta del post: " + mm + " Para la ruta: " + miruta;
    return Response.status(200).entity(output).build();
  }
} 