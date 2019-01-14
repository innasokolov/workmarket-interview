package com.workmarket.interview;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressReader {

        private List<String> addresses;

        public AddressReader(File addressFile) {
            super();
            init(addressFile);
        }
        private void init(File file) {
            InputStream inputStream = null;
            addresses = new ArrayList<>();
            try {
                inputStream = new FileInputStream(file);
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(inputStream));
                String nextLine;

                while ((nextLine = reader.readLine()) != null) {
                    addresses.add(nextLine);
                }
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Error reading addresses from the file: " + e);
            } finally {
                if (inputStream != null) try {
                    inputStream.close();
                } catch (IOException e) {
                    //ignore;
                }
            }
        }

    public List<String> getAddresses() {
        return addresses;
    }
}
