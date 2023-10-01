package prj_hola;

import java.text.*;
import java.util.*;

class Reloj extends Thread {
  int cuenta = 0;

  public Reloj(String nombre, int cuenta) {
    super(nombre);
    this.cuenta = cuenta;
  }

  public void start() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_YEAR, 2);
    Date fecha = cal.getTime();

    System.out.println(getName() + "-> " +
        "La fecha es ahora: " + fecha.toString());
    System.out.println(getName() + "-> " +
        DateFormat.getTimeInstance(3, Locale.FRANCE).format(fecha) +
        " Faltan " + cuenta + " seg. para la alarma");

    super.start();
  }

  public void run() {
    for (int i = 1; i <= cuenta; i++) {
      // EJERCICIO: Provoque un retraso de 1000 milisegundos
      long miles = 1000;
      try {
        super.sleep(miles);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println(getName() + ": Riiinnnng!!!");
  }
}

public class Relojes {
  public static void main(String[] args) {
    // EJERCICIO: Cree dos instancias de la clase Reloj
    Reloj s = new Reloj("Sergio", 10);

    Reloj c = new Reloj("Carlos", 8);
    s.start();
    c.start();
  }
}