package de.supresswarnings.tok.data;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesHandlerTest {

    @Test
    public void test(){
        String language1 = "german";
        String language2 = "wubba dubba lub lub";

        PropertiesHandler propertiesHandler = new PropertiesHandler();

        propertiesHandler.setLanguage(language1);
        Assert.assertSame(language1, propertiesHandler.getLanguage());

        propertiesHandler.setLanguage(language2);
        Assert.assertSame(language1, propertiesHandler.getLanguage());
    }
}
