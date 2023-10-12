package server;

public class serverEcho {
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
        if (tProxy == Proxy.INDIRECTO) {
            props.setProperty("Ice.Default.Locator", "IceGrid/Locator:tcp -h localhost -p 12000");
            props.setProperty("EchoAdapter.Endpoints", "tcp");
            props.setProperty("EchoAdapter.AdapterId", "EchoAdapter");
        }
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
            // Create adapter.
            com.zeroc.Ice.ObjectAdapter adapter = tProxy == Proxy.INDIRECTO
                    ? ic.createObjectAdapter("EchoAdapter")
                    : ic.createObjectAdapterWithEndpoints("EchoAdapter", "default -h localhost -p 10000");

            //
            // Create servant and register it into the adapter.
            com.zeroc.Ice.Object iceObject = new EchoI();
            adapter.add(iceObject, com.zeroc.Ice.Util.stringToIdentity("Echo"));
            //
            // Generate a proxy to get its string form.
            com.zeroc.Ice.ObjectPrx proxy = adapter.createDirectProxy(com.zeroc.Ice.Util.stringToIdentity("Echo"));
            String s1 = ic.proxyToString(proxy);
            String type = tProxy == Proxy.INDIRECTO ? "INDIRECTO" : "DIRECTO";
            System.out.println("IOR Proxy: " + type + " (en servidor): " + s1);
            //
            // Activate adapter
            adapter.activate();
            // Block main thread.
            ic.waitForShutdown();
        }

    }

}
