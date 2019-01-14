package com.workmarket.interview;

import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Application {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java com.workmarket.interview.Application <json_output_file>");
            System.exit(1);
        }
        FileWriter outputFile = new FileWriter(args[0]);
        File inputFile = new File("src/main/resources/addresses.txt");

        AddressReader addressReader = new AddressReader(inputFile);
        List<String> addresses = addressReader.getAddresses();
        if (addresses == null || addresses.isEmpty()) {
            System.out.println("Check your check your address file, something might be amiss.");
            System.exit(1);
        }
        GeoLocationManager geoLocationManager = new GeoLocationManager();
        JSONArray jsonArray = new JSONArray();
        for (String nextAddress : addresses) {
            jsonArray.add(geoLocationManager.convert(nextAddress, geoLocationManager.locate(nextAddress)));
        }

        output(jsonArray.toJSONString(), outputFile);

        if (outputFile != null) outputFile.close();
    }

    private static void output(String text, FileWriter writer) throws IOException {
        if (writer != null) {
            writer.write(text + "\r\n");
            writer.flush();
        }
    }
}
