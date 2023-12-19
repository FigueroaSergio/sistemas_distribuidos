 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package datamodel;

public class Sampler extends Thread {
	
	public static long period = 500L;
	
	private boolean running = true;
	public void stopSampler() {running = false;}
	
	public Sampler() {
		super();
	}
	public void run() {
		while (running) {	
				
			for (Sensor sens : DaoSensors.instance.getModel().values()) {		
				sens.setValue(Math.random());
			}
	
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
