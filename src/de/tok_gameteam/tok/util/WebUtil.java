package de.tok_gameteam.tok.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Random;

public class WebUtil {

	public static ArrayList<String> getUrlSource(String url) throws IOException {
		ArrayList<String> returnList = new ArrayList<String>();
		returnList.clear();
		URL site = new URL(url);
		HttpURLConnection yc = (HttpURLConnection) site.openConnection();
		yc.addRequestProperty("User-Agent", getRandomUserAgent()); 
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			returnList.add(inputLine);
		}
		in.close();

		return returnList;
	}
	
	
	public static void downloadFileToDirectory(String url, File destination) throws Exception {
		URL website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		@SuppressWarnings("resource")
		FileOutputStream fos = new FileOutputStream(destination.getAbsolutePath());
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	}
	
	public static final String getRandomUserAgent() {
		String[] agents = new String[] {
				"Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.0)",
				"Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.1)",
				"Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 5.2)",
				"Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.0)",
				"Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.1)",
				"Opera/7.51 (Windows NT 5.0; U) [en]",
				"Opera/7.51 (Windows NT 5.1; U) [en]",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.22) Gecko/20110902 Firefox/3.6.22",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Tablet PC 2.0)",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.195.10 Safari/532.0",
				"Opera/9.80 (Windows NT 6.1; U; ru) Presto/2.9.168 Version/11.51",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/534.36 (KHTML, like Gecko) Chrome/12.0.742.53 Safari/534.36 QQBrowser/6.3.8908.201",
				"Opera/7.51 (Windows NT 5.2; U) [en]",
				"Opera/7.51 (Windows NT 6.0; U) [en]",
				"Opera/7.51 (Windows NT 6.1; U) [en]",
				"Mozilla/4.0 (compatible; MSIE 6.0; X11; Linux x86_64; ru) Opera 10.10",
				"Opera/9.80 (X11; Linux x86_64; U; ru) Presto/2.2.15 Version/10.10",
				"Mozilla/5.0 (Windows NT 5.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
				"Mozilla/5.0 (X11; U; Linux x86_64; ru; rv:1.9.0.4) Gecko/2008111611 Gentoo Iceweasel/3.0.4",
				"Mozilla/1.1 (compatible; MSPIE 2.0; Windows CE)",
				"Mozilla/1.10 [en] (Compatible; RISC OS 3.70; Oregano 1.10)",
				"Mozilla/1.22 (compatible; MSIE 2.0d; Windows NT)",
				"Googlebot", "MSNBot", "Yandex", "StackRambler",
		"Mozilla/1.22 (compatible; MSIE 5.01; PalmOS 3.0) EudoraWeb 2" };
		Random random = new Random();
		return agents[random.nextInt(agents.length)];
	}

	public static final String getRandomReferer() {
		String[] referers = new String[] { "http://www.google.com/",
				"http://www.yandex.ru/", "http://www.gigporno.ru/",
				"http://www.yahoo.com/", "http://www.votrube.ru/",
				"http://www.carderlife.ms/", "http://www.sex4porno.ru/",
				"http://www.hacker-pro.net/", "http://www.host-tracker.com/",
				"http://www.forum.antichat.ru/", "http://www.lenta.ru/",
				"http://www.wikpedia.org/", "http://www.hardcoreporn.com/",
				"http://www.mail.ru/", "http://www.vkontakte.ru/",
				"http://www.upyachka.ru/", "http://www.2ip.ru/",
				"http://www.webmoney.ru/", "http://www.live.com/",
				"http://www.podsos.com/", "http://www.libertyreserve.com/",
				"http://www.ebay.com/", "http://www.microsoft.com/",
				"http://www.ninemsn.com/", "http://www.pornhub.com/",
				"http://oce.leagueoflegends.com/", "http://minecraft.net/",
				"https://mojang.com/", "https://www.dropbox.com/" };
		Random random = new Random();
		return referers[random.nextInt(referers.length)];
	}
}