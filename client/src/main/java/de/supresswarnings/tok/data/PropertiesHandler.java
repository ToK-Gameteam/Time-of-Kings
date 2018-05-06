package de.supresswarnings.tok.data;

import java.io.*;
import java.util.HashSet;
import java.util.Properties;

public class PropertiesHandler {
    private Properties properties;
    private File propertiesFile;

    private HashSet<String> supportedLanguages = new HashSet<>();

    public static final int VERSION_NUMBER = 1;

    public PropertiesHandler(){
        properties = new Properties();

        propertiesFile = new File(System.getProperty("user.home") + File.separator + "ToK" + File.separator + "settings.prop");
        if(!propertiesFile.exists()){
            create();
        }

        try {
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            System.err.println("Can't load properties (Error Code: 100)");
            e.printStackTrace();
        }

        supportedLanguages.add("german");
        supportedLanguages.add("english");

        checkForUpdate();
    }

    private void create(){
        try{
            if(!propertiesFile.getParentFile().mkdirs()){
                throw new IOException("Can't create parent directory (Error Code: 101).");
            }
            if(!propertiesFile.createNewFile()){
                throw new IOException("Can't create properties file (Error Code 102).");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty("language", "default");
        properties.setProperty("version", VERSION_NUMBER + "");
        try(OutputStream output = new FileOutputStream(propertiesFile)){
            properties.store(output, "Properties file of Time of Kings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForUpdate(){
        if(VERSION_NUMBER > Integer.parseInt(properties.getProperty("version"))){
            properties.setProperty("version", VERSION_NUMBER + "");
        }
    }

    public void setLanguage(String newLanguage){
        if(supportedLanguages.contains(newLanguage)){
            properties.setProperty("language", newLanguage);
        }else{
            System.out.println("Language '" + newLanguage + "' is not supported (Error Code 103).");
        }
    }

    public String getLanguage(){
        return properties.getProperty("language");
    }
}
