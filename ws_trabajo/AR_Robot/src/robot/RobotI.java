package robot;

import com.zeroc.Ice.Current;

import agencia.datos.Escenario;
import agencia.datos.EstadoRobot;
import agencia.datos.Instantanea;
import agencia.datos.Posicion;
import agencia.datos.PuntosRobot;
import agencia.datos.suscripcion;
import agencia.objetos.CamaraPrx;
import agencia.objetos.RobotSeguidorPrx;
import comm.Difusion;
import comm.DifusionJMS;

import khepera.control.Braitenberg;
import khepera.control.Destino;
import khepera.control.Trayectoria;
import khepera.escenario.EscenarioKhepera;
import khepera.robot.IzqDer;
import khepera.robot.Polares;
import khepera.robot.RobotKhepera;

public class RobotI implements agencia.objetos.RobotSeguidor {

    CamaraPrx camaraProxy = null;
    private com.zeroc.Ice.Communicator iceComunicator;
    private int id;
    
    private String nombre = null;
    private String IOR = null;
    private Posicion objetivo;
    private Posicion posicion;
    private Polares polarPosicion = new Polares();
    private PuntosRobot puntos;
    
    private int lider;
    private String IORlider = null;
    private Posicion posicionLider = null;


    private khepera.escenario.EscenarioKhepera escenario;
    private RobotKhepera r;

    public RobotI(String rName, CamaraPrx camPrx,com.zeroc.Ice.Communicator ic) {

        nombre = rName;
        camaraProxy = camPrx;
        posicion = new Posicion(0, 0);
        escenario = new EscenarioKhepera();
        objetivo = new Posicion(500, 500);
        r = new RobotKhepera(posicion, escenario, 0);
        iceComunicator= ic;
    }

    public void setNombre(String nom) {
        nombre = nom;
    }

    public void setRobotIOR(String ior) {
        IOR = ior;
    }

    @Override
    public EstadoRobot ObtenerEstado(Current current) {

        return new EstadoRobot(nombre, id, IOR, puntos, objetivo, id);
    }

    @Override
    public void ModificarEscenario(Escenario esc, Current current) {
    	System.out.println("Nueva escenario");
        try {
            escenario = new EscenarioKhepera(esc);
        } catch (Exception e) {
            escenario = new EscenarioKhepera();
        }
        r = new RobotKhepera(posicion, escenario, 0);
    }

    @Override
    public void ModificarObjetivo(Posicion NuevoObj, Current current) {
    	System.out.println("Objetivo (" + NuevoObj.x+","+NuevoObj.y+")");
        objetivo = NuevoObj;
     
        
    }

    @Override
    public void ModificarPosicion(Posicion npos, Current current) {
    	System.out.println("Nueva posicion: (" + npos.x+","+npos.y+")");
        posicion = npos;
        r = new RobotKhepera(posicion, escenario, 0);
    }

    @Override
    public void ModificarLider(int idLider, Current current) {
    	System.out.println("Nuevo lider");
        this.lider = idLider;
    }

    public void avanzar() {
    	Posicion temp = posicionLider!=null?posicionLider:objetivo;
        
        //// Objetos para el control de "ir a objetivo"
        Trayectoria tra;
        Destino dst = new Destino();
        //// Objeto para el control de evitar obstaculos (usa el escenario)
        Braitenberg bra = new Braitenberg();
        // Acciones de control
        IzqDer nv = new IzqDer();
        IzqDer nv2 = new IzqDer();
        ////// aplica la accion de control
        r.avanzar();
        // obtiene la nueva posicion y puntos que definen el estado del robot.
        polarPosicion = r.posicionPolares();
        puntos = r.posicionRobot();

        // System.out.println(
        // "Posicion:" + polarPosicion.x + "," + polarPosicion.y + " posCentro:" +
        // puntos.centro.x + ","
        // + puntos.centro.y);
        /// Calcula la nueva accion de control
        tra = new Trayectoria(polarPosicion, temp);
        float[] ls = r.leerSensores();
        nv = dst.calcularVelocidad((Object) tra);
        nv2 = bra.calcularVelocidad((Object) ls);
        nv.izq += nv2.izq / 90;
        nv.der += nv2.der / 90;
        // Fija la accion de control para la nueva iteracion.
        r.fijarVelocidad(nv.izq, nv.der);
    }
    public void getLiderPosicion() {
    	if(IORlider == null) {
    		return;
    	}
    	  try {
    	 com.zeroc.Ice.ObjectPrx prx = iceComunicator.stringToProxy(IORlider);
    	 RobotSeguidorPrx robot = RobotSeguidorPrx.checkedCast(prx);
         if (robot == null) {
        	 throw new Error("Invalid proxy"); 
         }
         EstadoRobot liderEstado= robot.ObtenerEstado();
         posicionLider = new Posicion(liderEstado.puntrob.centro.x,liderEstado.puntrob.centro.y);
         objetivo = liderEstado.posObj;
         }
    	  catch(Exception e) {
         	 IORlider=null;
        	 posicionLider=null;
        	 return;
         }
        
    }
    public void start() {
    	System.out.println("Iniciando...");
        new RobotDifusion().start();
        new Move().start();

       
    }
    class Move extends Thread{
    	public void run() {
    	for (;;) {
    		getLiderPosicion();
         avanzar();
         try {
             Thread.sleep(100);
         } catch (Exception e) {

         }
     }
}
    }

    class RobotDifusion extends Thread {

        private Difusion difusion;
        private EstadoRobot sr;
        private suscripcion sus;

        public void run() {
            sus = camaraProxy.SuscribirRobot(IOR);
            
            id = sus.id;
            try {
                escenario = new EscenarioKhepera(sus.esc);
            } catch (Exception e) {
                escenario = new EscenarioKhepera();
            }
            r = new RobotKhepera(posicion, escenario, 0);
            System.out.println("My id: " + id);
            difusion = new DifusionJMS(sus.iport.ip, sus.iport.port);

            while (true) {
                Instantanea instantanea = (Instantanea) difusion.receiveObject();
                for (int i = 0; i < instantanea.estadorobs.length; i++) {
                    sr = instantanea.estadorobs[i];
                    //System.out.println("Contenido " + sr.id + ": " + IORlider != sr.IORrob);
                    if(sr.id==lider&& !IOR.equals(sr.IORrob )&&!sr.IORrob.equals(IORlider)) {
                    	System.out.println("Setting IOR Lider: " + sr.IORrob );
                    	IORlider=sr.IORrob;
                    	
                    	
                    }
                }

            }
        }
    } // de RobotDifusion

}
