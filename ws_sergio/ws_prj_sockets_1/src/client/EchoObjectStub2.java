package client;

import java.net.*;
import java.io.*;
import rmi.EchoInt;
import java.util.Timer;
import java.util.TimerTask;

public class EchoObjectStub2 implements EchoInt {

  private Socket echoSocket = null;
  private PrintWriter os = null;
  private BufferedReader is = null;
  private String host = "localhost";
  private int port = 7;
  private String output = "Error";
  private boolean connected = false;
  Timeout tout = null;

  public void setHostAndPort(String host, int port) {
    this.host = host;
    this.port = port;
    tout = new Timeout(10, this);
  }

  public String echo(String input) throws java.rmi.RemoteException {
    connect();
    if (echoSocket != null && os != null && is != null) {
      try {
        os.println(input);
        os.flush();
        output = is.readLine();
      } catch (IOException e) {
        System.err.println("I/O failed in reading/writing socket");
      }
    }
    programDisconnection();
    return output;
  }

  private synchronized void connect() throws java.rmi.RemoteException {
    // EJERCICIO: lo mismo que en EchoObjectStub
    if (host == null || connected) {
      return;
    }
    try {
      echoSocket = new Socket(host, port);
      this.is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
      this.os = new PrintWriter(echoSocket.getOutputStream());
      connected = true;
      // System.out.println("Connection success...");

    } catch (IOException e) {
      System.err.println("Error sending/receiving" + e.getMessage());
      e.printStackTrace();
    }
  }

  private synchronized void disconnect() {
    // EJERCICIO: lo mismo que en EchoObjectStub
    try {
      echoSocket.close();
      // System.out.println("Finish connection");
      connected = false;
    } catch (IOException e) {
      System.err.println("Error de comunicacion.");
    }
  }

  private synchronized void programDisconnection() {
    tout.start();
  }

  class Timeout {
    Timer timer;
    EchoObjectStub2 stub;
    int seconds;

    public Timeout(int seconds, EchoObjectStub2 stub) {
      this.seconds = seconds;
      this.stub = stub;
    }

    public void start() {
      // EJERCICIO

      this.cancel();
      timer = new Timer();
      TimeoutTask disconnect = new TimeoutTask(stub);
      this.timer.schedule(disconnect, seconds * 1000);
    }

    public void cancel() {
      // EJERCICIO
      if (timer == null) {
        return;
      }
      timer.cancel();
    }

    class TimeoutTask extends TimerTask {
      EchoObjectStub2 stub;

      public TimeoutTask(EchoObjectStub2 stub) {
        this.stub = stub;
      }

      @Override
      public void run() {
        // TODO Auto-generated method stub
        this.stub.disconnect();
      }
    }

  }
}
