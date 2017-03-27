package de.tok_gameteam.tok.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LanguageController {
	private Map<String, Locale> supportedLanguages;
	private ResourceBundle translation;

	public LanguageController(String language){
		supportedLanguages = new HashMap<String, Locale>();
		supportedLanguages.put("English", Locale.ENGLISH);
		supportedLanguages.put("German", Locale.GERMAN);
		translation = ResourceBundle.getBundle("language", supportedLanguages.get(language));
	}

	public String getString(String keyword){
		return translation.getString(keyword);
	}
}