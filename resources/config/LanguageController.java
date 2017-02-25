package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LanguageController {

	private Properties prop = new Properties();
	private InputStream input = null;

	public LanguageController(){
		try {

			input = getClass().getResourceAsStream("language_de.properties");

		    prop.load(input);
		    
		} catch (IOException ex) {
		    ex.printStackTrace();
		} finally {
		    if (input != null) {
		        try {
		            input.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
	
	public String getString(String key){
		return prop.getProperty(key);
	}
}

