package config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class StartUp {
	private File properties;

	public StartUp(){
		String path = System.getProperty("user.dir")
				+ File.separator + "ToK" + File.separator + "config.properties";
		properties = new File(path);
	}
	
	public void activate(){
		properties.getParentFile().mkdirs();
		try {
			properties.createNewFile();
			PrintWriter print = new PrintWriter(properties);
			print.println("language = default");
			print.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
