package prj_hola;

import java.io.*;

public class CountFile {
	public static void main(String[] args)
			throws java.io.IOException,
			java.io.FileNotFoundException {
		int count = 0;
		InputStream is = null;
		String filename;

		if (args.length >= 1) {
			// EJERCICIO: Cree una instancia de FileInputStream, llamada is,
			// para leer del fichero que se especifica como args[0]

			filename = args[0];
			is = new FileInputStream(filename);
		} else {
			is = System.in;
			filename = "Input";
		}

		/* EJERCICIO: */
		int c;
		while ((c = is.read()) != -1)
		// EJERCICIO: utilice un metodo de FileInputStream para leer un caracter
		{
			count++;
			if ((c == '\n' || c == '\r') && is.getClass() == System.in.getClass()) {
				break;
			}

		}

		System.out.println(filename + " has " + count
				+ " chars.");
		is.close();

	}

}