package com.marino.quietstone.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

class LoadHelper {
    private Logger logger = Logger.getLogger(LoadHelper.class.getSimpleName());
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
}