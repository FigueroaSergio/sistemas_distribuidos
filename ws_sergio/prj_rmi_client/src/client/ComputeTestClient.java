package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.compute.ComputeServerInt;
import interfaces.compute.TaskInt;

public class ComputeTestClient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static TaskInt echoTask = new TaskEcho();

	public static void main(String[] args) {
		String server_name = new String();
		if (args.length == 1) {
			server_name = "//" + args[0] + "/Compute";
		} else {
			server_name = "//localhost/Compute";
		}

		ComputeTestClient computeClient = new ComputeTestClient();
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter stdOut = new PrintWriter(System.out);

		String input, output;
		try {

			// EJERCICIO: "lookup" the Compute server RMI object
			ComputeServerInt echo = (ComputeServerInt) Naming.lookup(server_name);
			// EJERCICIO: load the task (computeClient.echoTask) to the computeServer
			System.out.println("lookup");
			
			TaskInt task = computeClient.echoTask;
			//System.out.println("calling");
			int id = echo.loadTask(task);
			//System.out.println("calling");
			echo.executeTask(task);
			echo.executeTask(task, "data");

			stdOut.print("> ");
			stdOut.flush();
			while ((input = stdIn.readLine()) != null) {

				// EJERCICIO: execute the loaded task. Get the response in "output"
				output = (String) echo.executeTask(id, input);

				stdOut.println(output);
				stdOut.print("> ");
				stdOut.flush();
			}
			echo.removeTask(id);
		} catch (Exception e) {
			System.out.println("Error en el cliente de echo RMI : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
