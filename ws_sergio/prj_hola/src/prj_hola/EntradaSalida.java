package prj_hola;

import java.io.*;

public class EntradaSalida {

  public static void main(String args[]) throws IOException {
    int j;
    byte[] buffer = new byte[80];
    String filename, filename2;
    float f1 = (float) 3.1416;
    float f2 = 0;

    try {
      // E/S con InputStream y OutputStream
      System.out.println("Teclee una cadena");
      j = System.in.read(buffer);
      System.out.print("La cadena: ");
      System.out.write(buffer, 0, j);

      // Convertimos cadena de bytes a cadena de caracteres (2 bytes)
      String tira = new String(buffer, 0, j);
      System.out.println("Otra vez la cadena: " + tira);

      // E/S con BufferedReader y PrintWriter
      // Conveniente con cadenas de caracteres (1 caracter = 2 bytes)
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter stdOut = new PrintWriter(System.out);

      // E/S con InputStream y OutputStream
      System.out.println("Teclee un entero");
      // EJERCICIO: Lea un entero por teclado e impr�malo en pantalla
      int number = Integer.parseInt(stdIn.readLine());
      stdOut.println(number);
      stdOut.flush();
      // E/S con BufferedReader y PrintWriter
      // Conveniente con cadenas de caracteres (1 caracter = 2 bytes)
      System.out.println("Teclee un nombre para un fichero");
      // EJERCICIO: Lea de teclado una cadena para el nombre del fichero
      // y almac�nela en la variable filename
      filename = stdIn.readLine();

      // E/S con ficheros y floats en formato num�rico
      // EJERCICIO: Escriba un float en el fichero filename (en formato binario)
      // EJERCICIO: Lea el float que ha escrito en el fichero filename
      // EJERCICIO: Escriba el float que ha leido del fichero filename en pantalla
      // AYUDA: Mire el c�digo de un poco mas abajo...
      // Es parecido (pero en formato de texto)
      filename2 = filename + ".bin";
      System.out.println("Fichero: " + filename2);

      DataOutputStream fout = new DataOutputStream(new FileOutputStream(filename2));
      BufferedReader fin = new BufferedReader(new FileReader(filename2));
      fout.writeBytes(Float.valueOf(f1).toString() + "\n");
      f2 = Float.valueOf(fin.readLine()).floatValue();
      System.out.println("Escrito y leido el float: " + f2 + " del fichero: " + filename2);

      // E/S con ficheros y floats en formato de texto
      filename2 = filename + ".txt";
      System.out.println("Fichero: " + filename2);

      PrintWriter fout2 = new PrintWriter(new FileOutputStream(filename2));
      BufferedReader fin2 = new BufferedReader(new InputStreamReader(new FileInputStream(filename2)));
      fout2.println(Float.valueOf(f1).toString());
      fout2.flush();
      // System.out.println(fin2.readLine());
      f2 = Float.valueOf(fin2.readLine()).floatValue();

      System.out.println("Escrito y leido el float: " + f2 + " del fichero: " + filename2);

      fout.close();
      fin.close();
      fout2.close();
      fin2.close();

    } catch (IOException e) {
      System.out.println("Error en E/S");
      System.exit(1);
    }

  }
}
