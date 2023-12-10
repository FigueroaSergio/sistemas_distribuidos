 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2023
 */

package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bindings.JsonBind;
import bindings.XmlBind;
import datamodel.Person;
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
		
		JsonBind jsonPersonBind = new JsonBind(Person.class);
		SimpleDateFormat dateFor = new SimpleDateFormat("MM-dd-yyyy");
		Person p;
		try {
			p = new Person("Sergio", "Figueroa",dateFor.parse("04-26-2001"));
			jsonPersonBind.toFile(p , "persona.json");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
