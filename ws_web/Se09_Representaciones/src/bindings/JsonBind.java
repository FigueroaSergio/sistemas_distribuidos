/*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2023
 */
package bindings;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

public class JsonBind {
	
	// Configuration of the json builder
    final JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonbuilder;
    Class classBind = null;
	
    /**
     * Constructor
     * @param classToBind
     */
	public JsonBind(Class classToBind) {
		classBind = classToBind;
		jsonbuilder = JsonbBuilder.newBuilder().withConfig(config).build();
	}
	
	/**
	 * Guardar objeto en un archivo (JSON)
	 * @param obj
	 * @param filename
	 */
	public void toFile(Object obj, String filename) {
		// Obtener string json
		String resultJson = jsonbuilder.toJson(obj);
		//Guardar en archivo
		try {
			PrintWriter pw = new PrintWriter(new BufferedOutputStream( new FileOutputStream(filename)));
			pw.print(resultJson);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Obtener objeto desde un archivo (JSON)
	 * @param obj
	 * @param filename
	 */
	public Object fromFile(String filename) {
		
		Object retval = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String resultJson = "";
			String line = "";
			while ( ( line = br.readLine()) != null ) resultJson += line;
			// parse
			retval = jsonbuilder.fromJson(resultJson, classBind);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retval;
	}
	/**
	 * Obtener representacion de un objeto en un string (JSON)
	 * @param obj
	 */
	public String toJSONString(Object obj) {
		
		String retvalJSON = "";
		retvalJSON = jsonbuilder.toJson(obj);
		return retvalJSON;
	}
	
	/**
	 * Obtener objeto desde un string (JSON)
	 * @param obj
	 */
	public Object fromJSONString(String jsonString) {
		
		Object retval = null;
		retval = jsonbuilder.fromJson(jsonString, classBind);
		return retval;
	}
}
///////////////////////////////////////////////////////////////////////////////

