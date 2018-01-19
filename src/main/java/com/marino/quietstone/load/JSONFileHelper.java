package com.marino.quietstone.load;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class JSONFileHelper implements JSONHelper {
    private Logger logger = Logger.getLogger(JSONFileHelper.class.getSimpleName());
    /**
     * Reads a file to a String
     *
     * @param filename name of the file
     * @return FileReader containing the file or null
     */
    FileReader readFile(final String filename) {
        FileReader reader = null;
        try {
            //Get file from resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(filename).getFile());
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Error FileNotFoundException {0}", e);
        }
        return reader;
    }

    @Override
    public BufferedReader getJSON(final String origin) {
        BufferedReader reader = null;
        try {
            //Get file from resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(origin).getFile());
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Error FileNotFoundException {0}", e);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.SEVERE, "Error UnsupportedEncodingException {0}", e);
        }
        return reader;
    }
}