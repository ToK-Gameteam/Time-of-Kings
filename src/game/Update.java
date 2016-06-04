package game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.ProgressMonitorInputStream;

import game.util.WebUtil;

public class Update {
	public static String UPDATE_URL = "https://gist.githubusercontent.com/DeathsGun/83fc464b2fed10a064349a7bee8b726b/raw/9cc34ff3b4db376d234253c81b14feec86f1121d/gistfile1.txt";
	public static double GAME_VERSION = 0.0;
	public static void checkUpdate() throws IOException {
		ArrayList<String> version = WebUtil.getUrlSource(UPDATE_URL);

		boolean listen = false;
		for(String s : version) {
			s = s.replaceAll("\\<[^>]*>","").trim();
			if(s.equalsIgnoreCase("START"))
				listen = true;
			if(s.equalsIgnoreCase("END"))
				listen = false;

			if(listen) {
				if(s.contains("VERSION")) {
					String[] splitter = s.split("=");
					double availableVersion = Double.parseDouble(splitter[1]);
					if(availableVersion > GAME_VERSION) {
					} else {
						System.out.println("No update found!");
					}
				}
			}
		}
	}
	public static void downloadUpdate(Double version) throws Exception {
		String filename = "Village.jar"; //Jar Name
        URL url = new URL("" + version + filename);
        URLConnection uc = url.openConnection();
        InputStream is = (InputStream) uc.getInputStream();
        ProgressMonitorInputStream pmis = new ProgressMonitorInputStream( null, "Downloading Update ...", is );
        pmis.getProgressMonitor().setMaximum( uc.getContentLength() );
        
        FileOutputStream out = new FileOutputStream("Village.jar");
        
        byte[] buffer = new byte[1024];
        for( int n; (n = pmis.read(buffer)) != -1; out.write(buffer, 0, n) );
        
        pmis.close();
        out.close();
        }
}
