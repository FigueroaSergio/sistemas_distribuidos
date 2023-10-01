/*
 *  TestClient.java
 *
 *  @author Jose Simo. (c) ai2-DISCA-UPV Creative Commons.
 *  
 *  Usage of example message format.
 *  Rev: 2017
 */
package test;

import java.io.IOException;
import java.net.Socket;
import message.Message;


public class TestClient {

	public static void main(String[] args) {

		System.out.println("Float processing client.");
		//
		String host = "localhost";
		int port = 8000;
		//
		Socket s;
		try {
			s = new Socket(host, port);
			System.out.println("Connection established.");
			
		} catch (IOException e) {
			System.err.println("Connection error.");
			return;
		}
		//
		float f = 3.141592F;
		//
		for (int i=0; i<3; i++){
			try {
				//
				System.out.println("Query(" + i + "): " + f);
				// Send query message
				byte[] buffer = util.Bin.floatToDWord(f);
				Message mOut = new Message(buffer);
				mOut.writeToOutputStream(s.getOutputStream());
				// Receive response
				Message mIn = new Message();
				mIn.readFromInputStream(s.getInputStream());
				f = util.Bin.dWordToFloat(mIn.payload);
				//
				System.out.println("Response: " + f);
				
			} catch (IOException e) {
				System.err.println("Communication error.");
			}
		}
		//
		try {
			s.close();
		} catch (IOException e) {
			System.err.println("Clossing socket error.");
		}
	}
}
