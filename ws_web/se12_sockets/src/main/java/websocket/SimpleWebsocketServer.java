 /*
 *  SimpleWebsocketServer.java
 *
 *  @author Jose Simo. (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 *  
 */

package websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


/**
 * Server end point of the websocket.
 */
@ServerEndpoint(value="/sensor") 
public class SimpleWebsocketServer {

	private static Set<Session> sessions = new HashSet<>();
	private static Set<Session> activeSessions = new HashSet<>();
	private static Thread sampligThread = null;
	
	/**
	 * Service object constructor
	 */
    public SimpleWebsocketServer() {
    	super();
    	System.out.println("simpleWebsocketServer:constructor: New object.");
    }
    
    /**
     * Connection callback. It runs when a client opens the websocket.
     * @param session. State holder. Related to a connected user/Session.
     */
    @OnOpen
    public void onOpen(Session session) {
    	
    	System.out.println("SimpleWebsocketServer:onOpen:" );
    	/////////////////////////////////////////////////////////////
    	// Store session into the static set 
        synchronized(sessions) { sessions.add(session); }
    	/////////////////////////////////////////////////////////////
    	// Start the static communication thread 
    	if (sampligThread == null) {
    		sampligThread = (Thread) new SensorServiceThread(500); //500 ms sampling period.
    		sampligThread.start();
    	}
        //
    }

    /**
     * Connection down callback. It runs when a client closes the websocket.
     * @param session. State holder. Related to a connected user/Session.
     */
    @OnClose
    public void onClose(Session session) {

        System.out.println("SimpleWebsocketServer:onClose: Session closed: " + session.getId());
	    /////////////////////////////////////////////////////////////
    	// Remove session from the static sets 
        synchronized(sessions) { sessions.remove(session); }
        synchronized(activeSessions) { activeSessions.remove(session); }
        //
    }
    
    /**
     * Error callback.
     * @param e
     */
    @OnError
    public void onError(Throwable e) {
    	
    	System.out.println("SimpleWebsocketServer:onError: Session error: " + e.getMessage());
        e.printStackTrace();
    }
    
    /**
     * Received message callback.
     * 
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        
        ///////////////////////////////////////////////////////////////////////////////
        /// Handle the websocket received messages 
    	System.out.println("SimpleWebsocketServer::onMessage: New message ==>  " + message);
        ///////////////////////////////////////////////////////////////////////////////
        // Handle "start" message. Add the session into the activeSessions.
        if (message.contains("start")) {	
        	synchronized(activeSessions) { 
	        	activeSessions.add(session);
	        	activeSessions.notifyAll();
        	}
        }
        ///////////////////////////////////////////////////////////////////////////////
        // Handle "stop" message. Remove the session from the activeSessions
        if (message.contains("stop")) {
        	synchronized(activeSessions) { 
        		activeSessions.remove(session); 
        	}
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
	//// Internal thread class
	/////////////////////////////////////////////////////////////////////////////////////////
    /**
     * The server end point service creates one thread based on this class.
     * This thread read sensor information and sends it to all active sessions.
     * Currently the sensor information is a random float number.
     *
     */
	public class SensorServiceThread extends Thread {
		
		private boolean m_running;
		private int m_periodMs;
		private int m_count = 0;		
		/**
		 * Constructor.
		 */
		public SensorServiceThread(int period) {
			m_running = true;
			m_periodMs = period;
		}
		/**
		 * Propose the thread termination
		 */
		public void terminateThread() {
			m_running = false;
		}
		/**
		 * Main thread loop.
		 */
		public void run() {
			//////////////////////////////////////////////////////
			// Main message stream loop
			while (m_running) {
				//////////////////////////////////////////////////////
            	//Elaborate the sensor message (random value)
            	String messOutStr = "{ \"SensID\":\"" + "SENSOR" + "\"," + 
            						" \"count\":" + m_count + "," +
            						" \"value\":" + Math.random() + "}";
            	m_count++;
	    		//////////////////////////////////////////////////////
	    		// Get a list of active sessions
	    		ArrayList<Session> curentActiveSessions = new ArrayList<Session>();
	    		synchronized(activeSessions) {	
	        		for (Session s : activeSessions) {
	        			curentActiveSessions.add(s);
	        		}
	    		}
	    		//////////////////////////////////////////////////////
	    		// Send message for each active session of the websocket.
	    		int sentCount = 0;
	            for (Session s : curentActiveSessions) {
	            	if (s.isOpen()) {
            			try {
            	    		//////////////////////////////////////////////////////
							s.getBasicRemote().sendText(messOutStr);
							sentCount++;
				    		//////////////////////////////////////////////////////
						} catch (IOException e) {
							//////////////////////////////////////////////////////
							//Remove the session from the static sets.
							System.out.println("simpleWebsocketServer::SensorServiceThread: Error sending to websocket session. Removing session.");
				        	synchronized(activeSessions) { 
				        		activeSessions.remove(s); 
				        	}
				        	synchronized(sessions) { 
				        		sessions.remove(s); 
				        	}
				        	//////////////////////////////////////////////////////							
						}	            		
	            	}
	            } 
	            /////////////////////////////////////////////////////////////
	        	/////////////////////////////////////////////////////////////
	        	// If no receivers, wait for new active receivers notification. 
	        	if (sentCount == 0) {
	        		try { 
		        		synchronized (activeSessions) {
		        			activeSessions.wait(); 
		        		}
	        		} catch (InterruptedException e) {;}
	        	} else {
	        		try {
						Thread.sleep(m_periodMs);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	        	}
	        	//////////////////////////////////////////////////////
			} // of running loop
			System.out.println("simpleWebsocketServer::SensorServiceThread: Exiting thread.");
		} // of run method
	} //of thread class
} //of end point class
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////

