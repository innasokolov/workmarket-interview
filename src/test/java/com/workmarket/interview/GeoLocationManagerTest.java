package com.workmarket.interview;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;

public class GeoLocationManagerTest {

    @Test
    public void testConvertFound() throws  Exception {
        File file = new File("src/test/resources/geolocation-found.json");
        FileReader reader = new FileReader(file);
        char[] buffer = new char[(int) file.length()];
        reader.read(buffer, 0, (int) file.length());
        String json = new String(buffer);
        GeoLocationManager manager = new GeoLocationManager();
        JSONObject result = manager.convert("777 Brockton Avenue, Abington MA 2351", json);
        Assert.assertEquals("{\"address\":\"777 Brockton Avenue, Abington MA 2351\",\"location\":{\"lng\":-70.96849999999999,\"lat\":42.0963},\"status\":\"FOUND\"}", result.toJSONString());
    }

    @Test
    public void testConvertNotFound() throws  Exception {
        File file = new File("src/test/resources/geolocation-notfound.json");
        FileReader reader = new FileReader(file);
        char[] buffer = new char[(int) file.length()];
        reader.read(buffer, 0, (int) file.length());
        String json = new String(buffer);
        GeoLocationManager manager = new GeoLocationManager();
        JSONObject result = manager.convert("777 NotFound Avenue, Nowhere MA 2351", json);
        Assert.assertEquals("{\"address\":\"777 NotFound Avenue, Nowhere MA 2351\",\"status\":\"NOT_FOUND\"}", result.toJSONString());
    }

    @Test
    public void testConvertEmptyJson() {
        String json = "";
        GeoLocationManager manager = new GeoLocationManager();
        JSONObject result = manager.convert("666 Six Avenue, Sixville SV 66666", json);
        Assert.assertEquals("{\"address\":\"666 Six Avenue, Sixville SV 66666\",\"status\":\"NOT_FOUND\"}", result.toJSONString());
    }
}
