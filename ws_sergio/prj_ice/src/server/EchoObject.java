package server;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class EchoObject {
    String myURL = "localhost";

    public String echo(String input) {
        Date h = new Date();
        String fecha = DateFormat.getTimeInstance(3, Locale.FRANCE).format(h);
        String ret = myURL + ":" + fecha + "> " + input;
        try {
            Thread.sleep(3000);
            ret = ret + " (retrasada 3 segundos)";
        } catch (InterruptedException e) {
        	
        }

        return ret;
    }
}