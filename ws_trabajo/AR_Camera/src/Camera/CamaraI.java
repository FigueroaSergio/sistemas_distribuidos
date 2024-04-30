/*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package Camera;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import com.zeroc.Ice.Current;

import agencia.datos.Escenario;
import agencia.datos.EstadoRobot;
import agencia.datos.IPYPort;
import agencia.datos.Instantanea;
import agencia.datos.ListaSuscripcion;
import agencia.datos.suscripcion;
import agencia.objetos.RobotSeguidorPrx;
import agencia.objetos.ConsolaPrx;
import comm.Difusion;
import comm.DifusionJMS;
import comm.DifusionMulticast;

/**
 * 
 */
public class CamaraI implements agencia.objetos.Camara {

  private LinkedList<String> listaRobots = new LinkedList<String>();
  private int nRobots;
  private LinkedList<EstadoRobot> listaEstados = new LinkedList<EstadoRobot>();
  private LinkedList<String> listaConsolas = new LinkedList<String>();
  private int nConsolas;

  Instantanea instantanea;
  

  private IPYPort ipyport;

  private com.zeroc.Ice.Communicator iceComunicator;
  private Escenario escenario;

  ///////////////////////////////////////////////////////////////////////////////////////////////

  public CamaraI(com.zeroc.Ice.Communicator ic, IPYPort iport) {

    iceComunicator = ic;
    ipyport = new IPYPort(iport.ip, iport.port);
    nRobots=0;
    nConsolas=0;
  }

  public void start() {

    new CamaraDifusion(ipyport);
  }

  class CamaraDifusion {
    private Timer timer;
    private Difusor df;
    private int periodMS = 500;

    public CamaraDifusion(IPYPort iport) {
      timer = new Timer();
      df = new Difusor(iport);
      timer.schedule(df, 0, periodMS);
    }

    // ------------------------------------------------------------------------------
    class Difusor extends TimerTask {

      private Difusion difusion;

      //
      public Difusor(IPYPort iport) {
        //
        try {
          difusion = new DifusionJMS(iport.ip, ipyport.port);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      public void run() {
        EstadoRobot st = new EstadoRobot();
        LinkedList<String> listaFallos = new LinkedList<String>();
        //
        listaEstados.clear();
        listaFallos.clear();
        LinkedList<String> listaRobotsCopia = (LinkedList<String>) listaRobots.clone();

        for (Iterator<String> i = listaRobotsCopia.iterator(); i.hasNext();) {
          String ior = null;
          try {
            ior = (String) i.next();
            com.zeroc.Ice.ObjectPrx prx = iceComunicator.stringToProxy(ior);
            RobotSeguidorPrx robot = RobotSeguidorPrx.checkedCast(prx);
            if (robot == null) {
              throw new Error("Invalid proxy");
            }
            listaEstados.add(robot.ObtenerEstado());

          } catch (Exception e) {
            System.out.println("Detectado fallo x en Robot: " + ior);
            listaFallos.add(ior);

          }
        }
        //
        for (Iterator<String> i = listaFallos.iterator(); i.hasNext();) {
          listaRobots.remove(i.next());
        }
        instantanea = new Instantanea((EstadoRobot[]) listaEstados.toArray(new EstadoRobot[0]));
        difusion.sendObject(instantanea);

      } // de run
    } // de clase difusor
  } // de clase CamaraDifusion

  @Override
  public suscripcion SuscribirConsola(String IORcons, Current current) {
    listaConsolas.add(IORcons);
    nConsolas+=1;
    System.out.println("Nueva consola " + IORcons);
    
    return new suscripcion(nConsolas, ipyport, this.ObtenerEscenario(current));
    
  }

  @Override
  public void BajaRobot(String IORrob, Current current) {
      System.out.println("Baja robot " + IORrob);

    listaRobots.removeIf(ior -> ior == IORrob);

  }

  @Override
  public void BajaConsola(String IORcons, Current current) {
	  System.out.println("Baja consola " + IORcons);
    listaRobots.removeIf(ior -> ior == IORcons);
  }

  @Override
  public ListaSuscripcion ObtenerLista(Current current) {
    return new ListaSuscripcion((String[])listaRobots.toArray() ,(String[])listaConsolas.toArray());
  }

  @Override
  public IPYPort ObtenerIPYPortDifusion(Current current) {
    return ipyport;
  }

  @Override
  public Instantanea ObtenerInstantanea(Current current) {
    return instantanea;
  }

 

  @Override
  public Escenario ObtenerEscenario(Current current) {
    return escenario;
  }

  @Override
  public suscripcion SuscribirRobot(String IORrob, Current current) {
    listaRobots.add(IORrob);
    nRobots+=1;
    System.out.println("Nueva robot " + IORrob);
    return new suscripcion(nRobots, ipyport, escenario);
    
  } 
  
  @Override
  public void ModificarEscenario(Escenario esc, Current current) {
    escenario = esc;
    LinkedList<String> listaFallos = new LinkedList<String>();

    listaFallos.clear();
    LinkedList<String> listaRobotsCopia = (LinkedList<String>) listaRobots.clone();

    for (Iterator<String> i = listaRobotsCopia.iterator(); i.hasNext();) {
      String ior = null;
      try {
        ior = (String) i.next();
        com.zeroc.Ice.ObjectPrx prx = iceComunicator.stringToProxy(ior);
        RobotSeguidorPrx robot = RobotSeguidorPrx.checkedCast(prx);
        if (robot == null) {
          throw new Error("Invalid proxy");
        }
        robot.ModificarEscenario(esc);

      } catch (Exception e) {
        System.out.println("Detectado fallo x en Robot: " + ior);
        listaFallos.add(ior);

      }
    }
    for (Iterator<String> i = listaFallos.iterator(); i.hasNext();) {
      listaRobots.remove(i.next());
    }
    
    LinkedList<String> listaConsolasCopia = (LinkedList<String>) listaConsolas.clone();
    listaFallos.clear();
    for (Iterator<String> i = listaConsolasCopia.iterator(); i.hasNext();) {
        String ior = null;
        try {
          ior = (String) i.next();
          com.zeroc.Ice.ObjectPrx prx = iceComunicator.stringToProxy(ior);
          ConsolaPrx consola = ConsolaPrx.checkedCast(prx);
          if (consola == null) {
            throw new Error("Invalid proxy");
          }
          System.out.println("Difusion escenario consola " + ior);
          consola.ModificarEscenario(esc);

        } catch (Exception e) {
          System.out.println("Detectado fallo x en Robot: " + ior);
          listaFallos.add(ior);

        }
      }
      for (Iterator<String> i = listaFallos.iterator(); i.hasNext();) {
        listaConsolas.remove(i.next());
      }

  }

}
