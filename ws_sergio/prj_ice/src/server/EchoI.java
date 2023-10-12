package server;

import java.rmi.RemoteException;

import com.zeroc.Ice.Current;

import sdi.echoIce.Echo;

public class EchoI implements Echo {
    EchoObject servObject = new EchoObject();
    
    

	@Override
	public String serviceEcho(String input, Current current) {
		return servObject.echo(input);
	}

}
