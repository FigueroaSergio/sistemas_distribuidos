 /*
 *  @author Jose Simo. 
 *  (c) ai2-UPV Creative Commons.
 *  Rev: 2022
 */
package services;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.core.Application;

public class SensorsApp extends Application{
	
	private datamodel.Sampler sampler = null;

     @Override
        public Set<Class<?>> getClasses() {
            return new HashSet<Class<?>>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
                System.out.println("Getting classes from SensorsApp...");
                // Add resources.
                add(SensorResource.class);
                add(SensorsResource.class);
                
                // Start additional active components
                if (sampler == null) {
                	sampler = new datamodel.Sampler();
                }
                if (!sampler.isAlive()) {
                	System.out.println("SensorsApp: Starting sampling thread.");
                	sampler.start();
                }
            }};
        }
}