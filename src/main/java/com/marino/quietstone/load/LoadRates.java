package com.marino.quietstone.load;

import com.marino.quietstone.model.Rate;

import java.util.ArrayList;
import java.util.List;

public class LoadRates {
    private List<Rate> loadRates() {
        String jsonData = LoadHelper.readFile("rates.json");
        JSONObject jobj = new JSONObject(jsonData);
        JSONArray jarr = new JSONArray(jobj.getJSONArray("keywords").toString());
        List<Rate> rates = new ArrayList<Rate>();
        return rates;
    }

}
