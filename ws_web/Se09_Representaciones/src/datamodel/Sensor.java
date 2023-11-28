 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package datamodel;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sensor")
@XmlAccessorType (XmlAccessType.FIELD)
public class Sensor {

	@XmlAttribute
	private String id;
	private double value = 0.0;
	private String description;
	
	public Sensor(){
	    
	}
	public Sensor(String idnew, String desc, double val){
		this.id = idnew;
		this.description = desc;
		this.value = val;
	}
		  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public synchronized double getValue() {
		return value;
	}
	public synchronized void setValue(double value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
