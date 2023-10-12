package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import sdi.echoIce.EchoPrx;

public class clientEcho {
    enum Proxy {
        DIRECTO,
        INDIRECTO
    }

    public static void main(String[] args) {
        // Get the initialized property set.
        Proxy tProxy = Proxy.DIRECTO;
        if (args.length >= 1) {
            if (args[0].equals("I"))
                tProxy = Proxy.INDIRECTO;

            if (args[0].equals("D"))
                tProxy = Proxy.DIRECTO;

        }
        com.zeroc.Ice.Properties props = com.zeroc.Ice.Util.createProperties(args);
        // Set props
        if (tProxy == Proxy.INDIRECTO)
            props.setProperty("Ice.Default.Locator", "IceGrid/Locator:tcp -h localhost -p 12000");
        //
        // Initialize a communicator with these properties.
        com.zeroc.Ice.InitializationData initData = new com.zeroc.Ice.InitializationData();
        initData.properties = props;
        //
        // Try with resources block.
        // Communicator "ic" is automatically destroyed
        // at the end of this try block
        // try (com.zeroc.Ice.Communicator ic = com.zeroc.Ice.Util.initialize(args))
        //
        try (com.zeroc.Ice.Communicator ic = com.zeroc.Ice.Util.initialize(initData)) {
            //
            // Install shutdown hook to (also) destroy communicator during JVM shutdown.
            // This ensures the communicator gets destroyed when the user interrupts the
            // application with Ctrl-C.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> ic.destroy()));
            //
            com.zeroc.Ice.ObjectPrx base = tProxy == Proxy.INDIRECTO
                    ? ic.stringToProxy("Echo@EchoAdapter")
                    : ic.stringToProxy("Echo:default -h localhost -p 10000");

            //

            String type = tProxy == Proxy.INDIRECTO ? "INDIRECTO" : "DIRECTO";
            // Show proxy as string
            String ior = ic.proxyToString(base);
            System.out.println("iceStrIOR Proxy: " + type + " (en cliente): " + ior);
            //
            // Narrow proxy.
            EchoPrx echo = EchoPrx.checkedCast(base);
            if (echo == null) {
                throw new Error("Invalid proxy");
            }
            // TODO: Implementa echo
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter stdOut = new PrintWriter(System.out);
            String input, output;
            try {
            	while (true) {
                    // EJERCICIO: Leer de teclado //EJERCICIO: Invocar el stub
                    input = stdIn.readLine();
                    output = echo.serviceEcho(input);
                    // EJERCICIO: Imprimir por pantalla
                    stdOut.println(output);
                    stdOut.flush();
                }
            }catch(IOException e){
            	System.out.print(e.getMessage());
            }
            

        }
    }

}
