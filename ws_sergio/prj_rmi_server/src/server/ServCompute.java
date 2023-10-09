package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import interfaces.compute.ComputeServerInt;
import interfaces.compute.TaskInt;
import interfaces.echo.EchoInt;

public class ServCompute implements ComputeServerInt {

	// EJERCICIO: define a Tasks container (p.e. an ArrayList)
	private ArrayList<TaskInt> tasks = new ArrayList<>();

	protected ServCompute() {
		super();
	}

	@Override
	public int loadTask(TaskInt a) throws RemoteException {
		System.out.println("Loading TASK");
		// EJERCICIO: add task to the defined container
		tasks.add(a);
		return tasks.size() - 1;// EJERCICIO: returns an appropriate taskid
	}

	@Override
	public int removeTask(int idx) throws RemoteException {
		System.out.println("Removing TASK");
		// EJERCICIO: remove the task from the container
		if (idx >= 0 && idx < tasks.size()) {
			tasks.remove(idx);
			return 0;
		}
		return 0;
	}

	@Override
	public Object executeTask(TaskInt a) throws RemoteException {
		System.out.println("Executing a Task");
		// EJERCICIO: execute the passed task

		return a.execute(); // EJERCICIO:
	}

	@Override
	public Object executeTask(TaskInt a, Object params) throws RemoteException {
		System.out.println("Loading and executing a task with param(" + params + ")");
		// EJERCICIO: execute the passed task
		
		return a.execute(params);// EJERCICIO:
	}

	@Override
	public Object executeTask(int idx, Object params) throws RemoteException {
		System.out.println("Executing the task " + idx + " with param(" + params + ")");
		// EJERCICIO: execute the previously loaded task
		if (idx >= 0 && idx < tasks.size()) {
			TaskInt task = tasks.get(idx);
			return task.execute(params);
		} // EJERCICIO:
		return null;
	}
	// rmiregistry -J-Djava.rmi.server.codebase="file:///home/sergiok/sdi/ws_sergio/prj_rmi_interfaces/bin/"
	// añadir en vm options -Djava.rmi.server.codebase=file:///home/sergiok/sdi/ws_sergio/prj_rmi_client/bin/
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}


		try {
			// EJERCICIO: get the local registry
			Registry registry = LocateRegistry.getRegistry();
			// EJERCICIO: build the ServCompute stub
			ComputeServerInt stub = (ComputeServerInt) UnicastRemoteObject.exportObject(new ServCompute(), 0);

			// EJERCICIO: bind (or rebind) the stub into the local registry
			registry.rebind("Compute", stub);

		} catch (RemoteException e) {
			System.err.println("Something wrong happended on the remote end");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		} catch (Exception e) {
			System.err.println("Something wrong");
			e.printStackTrace();
			System.exit(-1); // can't just return, rmi threads may not exit
		}
		System.out.println("The server is ready for tasks:");
	}
}
