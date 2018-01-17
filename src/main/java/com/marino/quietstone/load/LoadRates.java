package com.marino.quietstone.load;

import com.marino.quietstone.model.Rate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadRates {
    public HashMap<String, Rate> loadRates() {
        String jsonData = LoadHelper.readFile("rates.json");
        JSONObject jobj = new JSONObject(jsonData);
        JSONArray jarr = new JSONArray(jobj.getJSONArray("keywords").toString());
        HashMap<String, Rate> rates = new HashMap<String, Rate>();
        return rates;
    }

}
