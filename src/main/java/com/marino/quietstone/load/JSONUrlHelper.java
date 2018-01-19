package com.marino.quietstone.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONUrlHelper implements JSONHelper {
    private Logger logger = Logger.getLogger(JSONUrlHelper.class.getSimpleName());

    @Override
    public BufferedReader getJSON(final String origin) {
        URL quietStone; // URL to Parse
        HttpURLConnection urlConnection;
        BufferedReader reader = null;
        try {
            quietStone = new URL(origin);
            urlConnection = (HttpURLConnection) quietStone.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, "Error MalformedURLException {0}", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error IOException {0}", e);
        }
        return reader;
    }
}
