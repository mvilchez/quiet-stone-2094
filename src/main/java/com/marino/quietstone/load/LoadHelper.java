package com.marino.quietstone.load;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadHelper {
    /**
     * Reads a file to a String
     *
     * @param filename name of the file
     * @return String containing the file
     */
    public static String readFile(final String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }
        return result;
    }
}