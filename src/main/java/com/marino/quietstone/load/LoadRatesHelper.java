package com.marino.quietstone.load;

import com.marino.quietstone.model.Rate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper to load rates from a JSON file called rates.json
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class LoadRatesHelper {
    private Logger logger = Logger.getLogger(LoadRatesHelper.class.getSimpleName());

    /**
     * Converts a json object to rate object
     *
     * @param rateJson json containing a json rate
     * @return Rate object
     */
    private static Rate parseRateObject(final JSONObject rateJson) {
        //Get rate from
        String from = (String) rateJson.get("from");

        //Get rate to
        String to = (String) rateJson.get("to");

        //Get rate
        String rate = (String) rateJson.get("rate");
        Rate rateObject = new Rate();
        rateObject.setFrom(from);
        rateObject.setTo(to);
        rateObject.setRateFee(Double.parseDouble(rate));
        return rateObject;
    }

    /**
     * Load rates from a json file
     *
     * @return lists of rates
     */
    public List< Rate> loadRates() {
        List<Rate> rates = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try {
            LoadHelper loadHelper = new LoadHelper();
            FileReader reader = loadHelper.readFile("rates.json");
            if (reader != null) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                JSONArray ratesList = (JSONArray) obj;

                //Iterate over rate array
                ratesList.forEach(rate ->
                        rates.add(parseRateObject((JSONObject) rate))
                );
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error IOException {0}", e);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error ParseException {0}", e);
        }
        return rates;
    }
}
