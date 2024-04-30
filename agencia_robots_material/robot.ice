///////////////////////////////////////////////////////////////////////
//// Module agencia

module agencia {

///////////////////////////////////////////////////////////////////////
//// Module agencia.khepera

  module datos {
	
      struct Posicion {
        float x;
        float y;
      } 
      
      sequence<Posicion> ListaPosiciones;
    
      struct Rectangulo {
        float x;
        float y;
        float ancho;
        float alto;
        int color;
      }

	  sequence<Rectangulo> ListaRectangulos;
	  
      struct Escenario {
        ListaRectangulos rects;
        int  nrecs;
        int color;
      }
    

      struct PuntosRobot{
        Posicion centro;
        ListaPosiciones sens; // son 9
        ListaPosiciones finsens; // son 9
        ListaPosiciones inter; // son 8
      }

      struct EstadoRobot {
        string nombre;
        int id;
        string IORrob;  //Referencia en formato String IOR
        PuntosRobot puntrob;
        Posicion posObj;
        int idLider;
      }

      sequence<EstadoRobot> ListaEstadosRobot;

      struct Instantanea {
          ListaEstadosRobot estadorobs;
      }
      
     struct IPYPort{
        string ip;
        int port;
     }

     struct suscripcion {
         int id;
         IPYPort iport;
         Escenario esc;
     }

	 sequence<string> ListaStrings;

     struct ListaSuscripcion{
       //IORs en formato string
       ListaStrings IORrobots;
       ListaStrings IORconsolas;
     }
}

///////////////////////////////////////////////////////////////////////
//// Module agencia.objetos

  module objetos {
  
    interface RobotSeguidor{
    
       agencia::datos::EstadoRobot ObtenerEstado( );
       void ModificarEscenario( agencia::datos::Escenario esc);  
       void ModificarObjetivo( agencia::datos::Posicion NuevoObj);
       void ModificarPosicion( agencia::datos::Posicion npos);
       void ModificarLider( int idLider);
    };

  

    interface Consola{
       void ModificarEscenario( agencia::datos::Escenario esc);
       bool estoyviva();
    };
  


     interface Camara{
     
        agencia::datos::suscripcion SuscribirRobot(string IORrob);
        agencia::datos::suscripcion SuscribirConsola(string IORcons);
        void BajaRobot(string IORrob);
        void BajaConsola(string IORcons);
        agencia::datos::ListaSuscripcion ObtenerLista();
        agencia::datos::IPYPort ObtenerIPYPortDifusion();
        agencia::datos::Instantanea ObtenerInstantanea();
        void ModificarEscenario(agencia::datos::Escenario esc);
        agencia::datos::Escenario ObtenerEscenario();
     };
  
  }
}

