 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2023
 */

package test;

import java.util.ArrayList;
import bindings.JsonBind;
import bindings.XmlBind;
import datamodel.Sensor;
import datamodel.Sensores;

public class BottomUpMarshal {

	public static void main(String[] args) {
		
		//////////////////////////////////////////////////////////////////
		// Ejemplo de contenido de datos
		//
		Sensor s1 = new Sensor("T001", "Temperatura horno de fermentacion", 74);
		Sensor s2 = new Sensor("T002", "Temperatura horno de coccion", 145);
		Sensor s3 = new Sensor("T003", "Temperatura horno de caramelizado", 85);
		
		Sensores misSensores = new Sensores();
		
		misSensores.setSensores(new ArrayList<Sensor>());
		misSensores.getSensores().add(s1);
		misSensores.getSensores().add(s2);
		misSensores.getSensores().add(s3);

		/////////////////////////////////////////////////////////////////
		// Serializacion en XML
		//
		XmlBind xmlBind = new XmlBind(Sensores.class);
		xmlBind.toFile(misSensores, "sensores.xml");
		/////////////////////////////////////////////////////////////////
		// Serializacion en JSON
		//
		JsonBind jsonBind = new JsonBind(Sensores.class);
		jsonBind.toFile(misSensores, "sensores.json");
		/////////////////////////////////////////////////////////////////
	}
}
