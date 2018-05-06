package de.supresswarnings.tok.data;

import org.junit.Assert;
import org.junit.Test;

public class StringContentTest {

    @Test
    public void test(){
        StringContent content = new StringContent();
        Assert.assertEquals("Hallo", content.getContent("hello"));
    }
}
