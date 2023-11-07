/*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */

package comm;

import java.io.*;
import java.net.*;

public class DifusionMulticast implements Difusion {

  MulticastSocket socket;
  String m_ip;
  int m_port;
  public InetSocketAddress group;

  public static final int BUFSIZE = 65535;

  // ------------------------------------------------------------------------------
  public DifusionMulticast(String ip, int port) {

    m_ip = ip;
    m_port = port;
    ///////////////////////////////////////////////////////////

    try {
      // EJERCICIO:
      // Crear el socket multicast
      socket = new MulticastSocket(port);
      // EJERCICIO:
      // Obtener la direccion del grupo
      group = new InetSocketAddress(ip, port);
      // EJERCICIO:
      // Unirse al grupo
      socket.joinGroup(group, null);
    } catch (IOException e1) {
      // TODO: handle exception
      e1.printStackTrace();
      return;
    }
  }

  ///////////////////////////////////////////////////////////

  // ------------------------------------------------------------------------------
  public Object receiveObject() {

    Object object = null;
    byte[] buffer = new byte[BUFSIZE];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    ///////////////////////////////////////////////////////////
    // EJERCICIO: recibir el paquete y deserializarlo

    try {
      socket.receive(packet);
      buffer = packet.getData();
      ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
      ObjectInputStream ois = new ObjectInputStream(bis);
      object = ois.readObject();
      ois.close();
      bis.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    ///////////////////////////////////////////////////////////
   

    return object;
  }

  // ------------------------------------------------------------------------------
  public void sendObject(Object object) {
    byte[] buffer;
    DatagramPacket packet;
   
    ///////////////////////////////////////////////////////////
    // EJERCICIO: serializar el paquete y difundirlo
    ///////////////////////////////////////////////////////////
    try {
    	 ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	    ObjectOutputStream oos = new ObjectOutputStream(bos);
    	    oos.writeObject(object);
    	    oos.flush();
    	    buffer = bos.toByteArray();
      packet = new DatagramPacket(buffer,
          buffer.length,
          InetAddress.getByName(m_ip),
          m_port);
      socket.send(packet);
    } catch (Exception e1) {
      e1.printStackTrace();
    }

  }
}