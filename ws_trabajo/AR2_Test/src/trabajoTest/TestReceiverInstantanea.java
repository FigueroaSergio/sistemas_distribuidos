/*
 *
 *  @author Jose Simo. (c) ai2-DISCA-UPV Creative Commons.
 *  
 *  Rev: 2017
 */
package trabajoTest;

import java.util.Properties;

import comm.DifusionJMS;

public class TestReceiverInstantanea {

    public static void main(String[] args) {
		///////////////////////////////////////////////////////////////////////////////////////////////
		Properties sys_props = System.getProperties(); 
		sys_props.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
    	try {  		
			DifusionJMS df = new DifusionJMS("228.0.0.10",5500);
	    	for(;;) {
	    		agencia.datos.Instantanea inst = (agencia.datos.Instantanea)df.receiveObject();
	    		for(int i=0; i<inst.estadorobs.length; i++) {
	    			agencia.datos.EstadoRobot sr = inst.estadorobs[i];
	    			String linea = "(" + sr.id + ";" + sr.IORrob + ";" + sr.nombre + ";" + sr.idLider + ";" + 
	    					"PosicionCentro:"+ sr.puntrob.centro.x + "," + sr.puntrob.centro.y + ")";
	    			System.out.println(linea);;
	    		}
	    	}
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
    }
    	   
}
