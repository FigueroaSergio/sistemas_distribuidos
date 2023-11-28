 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package datamodel;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sensores")
@XmlAccessorType (XmlAccessType.FIELD)

public class Sensores {
	
	@XmlElement(name="sensor")
	private List<Sensor> sensores = null;

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> misSensores) {
		this.sensores = misSensores;
	}

}
