 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package datamodel;

import java.util.HashMap;
import java.util.Map;

public enum DaoSensors {
	instance;
	  private Map<String, Sensor> contentProvider = new HashMap<String, Sensor>();
	  
	  private DaoSensors() {
	    
		Sensor sens = new Sensor("T00001", "Temperatura fusor PVC del inyector",0.0);
	    contentProvider.put("T00001", sens);
		sens = new Sensor("T00002", "Temperatura enfriadora",0.0);
	    contentProvider.put("T00002", sens);
		sens = new Sensor("T00003", "Temperatura horno de recocido",0.0);
	    contentProvider.put("T00003", sens);
	    
	  }
	  public Map<String, Sensor> getModel(){
	    return contentProvider;
	  }

}
