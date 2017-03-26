package de.tok_gameteam.tok.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesUpdate {
	private String path;
	
	private HashMap<String, String> validKeys;

	public PropertiesUpdate(){
		path = System.getProperty("user.dir")
				+ File.separator + "ToK" + File.separator + "config.properties";
		validKeys = new HashMap<String, String>();
		validKeys.put("language", "default");
		validKeys.put("balancing_version", DatabaseUpdate.BALANCING_VERSION-1 + "");
	}
	
	public void activate(){
		try {
			FileInputStream stream = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(stream);
			
			for(String key : validKeys.keySet()){
				if(!prop.containsKey(key)){
					prop.put(key, validKeys.get(key));
				}
			}

			FileOutputStream out = new FileOutputStream(new File(path));
			prop.store(out, null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
