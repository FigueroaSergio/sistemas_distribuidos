 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;

import datamodel.DaoSensors;
import datamodel.Sensor;

public class SensorResource {

	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String id;
	  public SensorResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	  }
	  
	  //Application integration     
	  @GET
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Sensor getSensor() {
	    Sensor sens = DaoSensors.instance.getModel().get(id);
	    if(sens==null)
	      throw new RuntimeException("Get: Sensor " + id +  " not found");
	    return sens;
	  }
	  
	  @GET
	  @Path("wait")
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Sensor getSensorWaited(
			  @DefaultValue("0.0") @QueryParam("value") double oldValue,
			  @DefaultValue("0.0") @QueryParam("error") double error,
			  @DefaultValue("500") @QueryParam("delay") int minDelay
			  ) {	
	    try {
			Thread.sleep(minDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    Sensor sens = DaoSensors.instance.getModel().get(id);
	    if(sens==null)
	      throw new RuntimeException("Get: Sensor " + id +  " not found");
	    sens.waitForValue(oldValue,error);
	    return sens;
	  }
	    
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response putSensor(JAXBElement<Sensor> sensor) {
	    Sensor sens = sensor.getValue();
	    return putAndGetResponse(sens);
	  }
	  
	  @DELETE
	  public void deleteSensor() {
	    Sensor sens = DaoSensors.instance.getModel().remove(id);
	    if(sens == null)
	      throw new RuntimeException("Delete: Sensor " + id +  " not found");
	  }
	   
	  
	  private Response putAndGetResponse(Sensor sens) {
	    Response res;
	    if(DaoSensors.instance.getModel().containsKey(sens.getId())) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    DaoSensors.instance.getModel().put(sens.getId(), sens);
	    return res;
	  }
	
}
