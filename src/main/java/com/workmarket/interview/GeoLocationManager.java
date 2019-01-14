package com.workmarket.interview;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GeoLocationManager {

    private String googleMapApi = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    public String locate(String address) {
        String urlString = null;
        StringBuilder response = new StringBuilder();
        try {
            urlString = googleMapApi + URLEncoder.encode(address, "UTF-8");
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            String contentType = urlConnection.getContentType();

            if (contentType != null && contentType.contains("application/json")) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();
            }

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + urlString + " : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Some went wrong connecting to: " + urlString + " : " + e.getMessage());
        }
        return response.toString();
    }

    public JSONObject convert(String address, String response) {
        JSONObject result = new JSONObject();
        result.put("address", address);
        String status = "NOT_FOUND";
        try {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(response);
            if (root.get("status").equals("OK")) {
                JSONArray results = (JSONArray) root.get("results");
                if (results != null && !results.isEmpty()) {
                    JSONObject firstResult = (JSONObject) results.get(0);
                    JSONObject geometry = (JSONObject) firstResult.get("geometry");
                    if (geometry != null) {
                        status = "FOUND";
                        result.put("location", geometry.get("location"));
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("Unable to parse JSON: " + response + " : " + e.getMessage());
        }
        result.put("status", status);
        return result;
    }
}
