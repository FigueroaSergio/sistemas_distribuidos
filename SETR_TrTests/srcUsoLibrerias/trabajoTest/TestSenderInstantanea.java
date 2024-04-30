/*
 *
 *  @author Jose Simo. (c) ai2-DISCA-UPV Creative Commons.
 *  
 *  Rev: 2017
 */
package trabajoTest;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Properties;

import comm.DifusionJMS;
import agencia.datos.EstadoRobot;
import agencia.datos.PuntosRobot;
import agencia.datos.Posicion;

public class TestSenderInstantanea {

    public static void main(String[] args) {
    	
		///////////////////////////////////////////////////////////////////////////////////////////////
		Properties sys_props = System.getProperties(); 
		sys_props.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
		///////////////////////////////////////////////////////////////////////////////////////////////
        String minombre = "MinervaX";
        int miid = 0;
        String miIOR = "DummyIORValue";
        //RobotSeguidorPrx miref = null; //Ojo!!
        PuntosRobot mipuntos;
	        Posicion centro = new Posicion(0,0);
	        Posicion[] sens = new Posicion[khepera.robot.ConfRobot.NSENSORES+1];
	        Posicion[] finsens = new Posicion[khepera.robot.ConfRobot.NSENSORES+1];
	        Posicion[] inter = new Posicion[khepera.robot.ConfRobot.NSENSORES];
	        for (int i=0; i<khepera.robot.ConfRobot.NSENSORES+1; i++) sens[i]= new Posicion(0,0);
	        for (int i=0; i<khepera.robot.ConfRobot.NSENSORES+1; i++) finsens[i]= new Posicion(0,0);
	        for (int i=0; i<khepera.robot.ConfRobot.NSENSORES; i++) inter[i]= new Posicion(0,0);
        mipuntos= new PuntosRobot(centro,sens,finsens,inter);
        Posicion miobj = new Posicion(500,500);
        int milider = -1;
        
    	LinkedList<EstadoRobot> listaEstados = new LinkedList<EstadoRobot>();
    	EstadoRobot estadoEjemplo = new EstadoRobot(
                minombre,
                miid,
                miIOR,
                mipuntos,
                miobj,
                milider
                );
    	
    	listaEstados.add(estadoEjemplo);
    	
    	agencia.datos.Instantanea inst = new agencia.datos.Instantanea((EstadoRobot[])(listaEstados.toArray(new EstadoRobot[0])));
    	try {
    		
			DifusionJMS df = new DifusionJMS("228.0.0.10",5500);
			for (int i=1; i<10;i++) {

				df.sendObject((Serializable)inst);
				Thread.sleep(200);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
    }
}
