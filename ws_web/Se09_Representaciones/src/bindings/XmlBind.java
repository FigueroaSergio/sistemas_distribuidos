/*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2023
 */
package bindings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlBind {
	
	JAXBContext jaxbContext;
	Marshaller jaxbMarshaller;
	Unmarshaller jaxbUnMarshaller;	
	
	public XmlBind(Class classToBind) {
		
		try {
			jaxbContext = JAXBContext.newInstance(classToBind);
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbUnMarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Guardar objeto en un archivo (XML)
	 * @param obj
	 * @param filename
	 */
	public void toFile(Object obj, String filename) {
		
		try {
			jaxbMarshaller.marshal(obj, new File(filename));
		} catch (JAXBException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Obtener objeto desde un archivo (XML)
	 * @param obj
	 * @param filename
	 */
	public Object fromFile(String filename) {
		
		Object retval = null;
		try {
			retval = jaxbUnMarshaller.unmarshal(new File(filename));	
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return retval;
	}
	/**
	 * Obtener representacion de un objeto en un string (XML)
	 * @param obj
	 */
	public String toXMLString(Object obj) {
		
		String retvalXML = "";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(obj, bos);
			retvalXML = bos.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return retvalXML;
	}
	
	/**
	 * Obtener objeto desde un string (XML)
	 * @param obj
	 */
	public Object fromXMLString(String xmlString) {
		
		Object retval = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(xmlString.getBytes());
			retval = jaxbUnMarshaller.unmarshal(bis);	
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return retval;
	}
}
