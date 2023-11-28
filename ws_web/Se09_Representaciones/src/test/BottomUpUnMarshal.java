 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2023
 */
package test;

import bindings.XmlBind;
import bindings.JsonBind;
import datamodel.Sensor;
import datamodel.Sensores;

public class BottomUpUnMarshal {

	public static void main(String[] args) {
	
		//Objetos pera recuperar la informacion de los archivos.
		Sensores misSensoresXML = null;
		Sensores misSensoresXML2 = null;
		Sensores misSensoresJSON = null;
		Sensores misSensoresJSON2 = null;
		/////////////////////////////////////////////////////////////////
		// Des-serializacion de XML
		//
		/// object from file
		XmlBind xmlBind = new XmlBind(Sensores.class);
		misSensoresXML = (Sensores) xmlBind.fromFile("sensores.xml");
		/// object to string
		String repXML = xmlBind.toXMLString(misSensoresXML);
		// object from string	
		misSensoresXML2 = (Sensores) xmlBind.fromXMLString(repXML);
		/////////////////////////////////////////////////////////////
		//////////// Visualizacion:
		System.out.println("Rep XML:\n" + repXML);
		System.out.println("Recuperado del archivo \"sensores.xml\":");
		if (misSensoresXML != null) {
			for (Sensor s: misSensoresXML.getSensores()) {
				System.out.println("Sensor:("+s.getId()+") -> "+s.getValue());
			}
		}
		//
		System.out.println("Recuperado del String:");
		if (misSensoresXML2 != null) {
			for (Sensor s: misSensoresXML2.getSensores()) {
				System.out.println("Sensor:("+s.getId()+") -> "+s.getValue());
			}
		}
		/////////////////////////////////////////////////////////////////
		// Des-serializacion de JSON
		//
		/// object from file
		JsonBind jsonBind = new JsonBind(Sensores.class);
		misSensoresJSON = (Sensores) jsonBind.fromFile("sensores.json");
		/// object to string
		String repJSON = jsonBind.toJSONString(misSensoresJSON);
		// object from string	
		misSensoresJSON2 = (Sensores) jsonBind.fromJSONString(repJSON);
		/////////////////////////////////////////////////////////////
		//////////// Visualizacion:
		System.out.println("Rep JSON:\n" + repJSON);
		System.out.println("Recuperado del archivo \"Sensores.json\":");
		if (misSensoresJSON != null) {
			for (Sensor s: misSensoresJSON.getSensores()) {
				System.out.println("Sensor:("+s.getId()+") -> "+s.getValue());
			}
		}
		//
		System.out.println("Recuperado del string json:");
		if (misSensoresJSON2 != null) {
			for (Sensor s: misSensoresJSON2.getSensores()) {
				System.out.println("Sensor:("+s.getId()+") -> "+s.getValue());
			}
		}
		/////////////////////////////////////////////////////////////////	
	}
}
