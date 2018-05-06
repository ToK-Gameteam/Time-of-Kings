package de.supresswarnings.tok.data;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class StringContent {
    private ResourceBundle stringContent;

    public StringContent(){
        Locale.setDefault(Locale.GERMAN);
        File stringContentDirectory = new File(System.getProperty("user.home") + File.separator + "ToK" + File.separator + "string_content");
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{stringContentDirectory.toURI().toURL()};
        } catch (MalformedURLException e) {
            System.err.println("Failed to load string content (Error Code 200).");
            e.printStackTrace();
        }
        ClassLoader loader = new URLClassLoader(urls);
        stringContent = ResourceBundle.getBundle("language", Locale.getDefault(), loader);
    }

    public String getContent(String key){
        return stringContent.getString(key);
    }
}
