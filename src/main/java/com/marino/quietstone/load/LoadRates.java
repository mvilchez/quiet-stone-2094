package com.marino.quietstone.load;

import com.marino.quietstone.model.Rate;
import com.marino.quietstone.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LoadRates {
    public List< Rate> loadRates() {
        List<Rate> rates = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
//Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("rates.json").getFile());
        try (FileReader reader = new FileReader(file)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray ratesList = (JSONArray) obj;

            //Iterate over rate array
            ratesList.forEach(rate -> {
                rates.add(parseRateObject((JSONObject) rate));
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rates;
    }


    private static Rate parseRateObject(JSONObject rateJson) {
        //Get rate from
        String from = (String) rateJson.get("from");

        //Get rate to
        String to = (String) rateJson.get("to");

        //Get rate
        String rate = (String) rateJson.get("rate");
        Rate rateObject = new Rate();
        rateObject.setFrom(from);
        rateObject.setTo(to);
        rateObject.setRate(Double.parseDouble(rate));
        return rateObject;
    }

}
