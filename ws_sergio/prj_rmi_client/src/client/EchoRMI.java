package client;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.echo.EchoInt;

public class EchoRMI {

	/**
	 * @param args
	 */
	@SuppressWarnings("removal")
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Uso echo <host>");
			System.exit(1);
		}
	
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter stdOut = new PrintWriter(System.out);
		String host = args[0];
		String input, output;
		try {
			// EJERCICIO: "lookup" the Echo RMI object
			Registry reg = LocateRegistry.getRegistry(host, 1099);
			EchoInt echo = (EchoInt) reg.lookup("echo");

			stdOut.print("> ");
			stdOut.flush();
			while ((input = stdIn.readLine()) != null) {

				// EJERCICIO: call echo RMI object
				output = echo.echo(input);

				stdOut.println(output);
				stdOut.print("> ");
				stdOut.flush();
			}
		} catch (Exception e) {
			System.out.println("RMI Echo Client error: " + e.getMessage());
		}
	}

}
