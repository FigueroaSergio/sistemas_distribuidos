/*
 *
 *  @author Jose Simo. (c) ai2-DISCA-UPV Creative Commons.
 *  
 *  Rev: 2017
 */
package trabajoTest;

import java.io.FileNotFoundException;

//import corba.instantanea.PuntosRobotD;
import agencia.datos.PuntosRobot;
//import corba.khepera.robot.PosicionD;
import agencia.datos.Posicion;
import khepera.control.Braitenberg;
import khepera.control.Destino;
import khepera.control.Trayectoria;
import khepera.escenario.EscenarioKhepera;
import khepera.robot.IzqDer;
import khepera.robot.Polares;
import khepera.robot.RobotKhepera;

public class TestAlgoritmosRobot {

    private khepera.escenario.EscenarioKhepera escenario;
    private RobotKhepera r;    
    private Posicion miobjetivo = new Posicion(500,500);
    private Polares mipos = new Polares();
    private PuntosRobot mipuntos;
    
    TestAlgoritmosRobot() {
    	//Posicion inicial del robot
    	Posicion posicionInicial = new Posicion(0,0);
    	//Crea el escenario con obstaculos
    	String filename = "dibujo";
        try {
			escenario= new EscenarioKhepera(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Archivo \"" + filename +"\" no encontrado. Tomando escenario por defecto.");
			escenario= new EscenarioKhepera();
		}
        //Crea el robot simulado
        r = new RobotKhepera(posicionInicial,escenario,0);
    }
    
    /**
     * Metodo que actualiza la posicion del robot y recalcula las 
     * acciones de control
     */
    public void avanzar(){
    	////Objetos para el control de "ir a objetivo"
        Trayectoria tra;
        Destino dst = new Destino();
        ////Objeto para el control de evitar obstaculos (usa el escenario)
        Braitenberg bra = new Braitenberg();
        //Acciones de control
        IzqDer nv= new IzqDer();
        IzqDer nv2= new IzqDer();
        //////aplica la accion de control
        r.avanzar();
        // obtiene la nueva posicion y puntos que definen el estado del robot.
        mipos = r.posicionPolares();
        mipuntos = r.posicionRobot();
        System.out.println("Posicion:" + mipos.x + "," + mipos.y + " posCentro:" + mipuntos.centro.x + "," +  mipuntos.centro.y);
        ///Calcula la nueva accion de control
        tra = new Trayectoria(mipos, miobjetivo);
        float[] ls=r.leerSensores();
        nv  = dst.calcularVelocidad((Object)tra);
        nv2 = bra.calcularVelocidad((Object) ls);
        nv.izq += nv2.izq/90; 
        nv.der += nv2.der/90;
        // Fija la accion de control para la nueva iteracion.
        r.fijarVelocidad(nv.izq,nv.der);
      }
	
    /**
     * 
     */
	public static void main(String[] args) {
		
		TestAlgoritmosRobot test = new TestAlgoritmosRobot();   
		for(;;) {
			/////
			test.avanzar();
			/////
			try {Thread.sleep(100);} catch (InterruptedException e) {}
		}    
	}
}
