package com.marino.quietstone.load;

import com.marino.quietstone.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LoadTransactions {
    public List<Transaction> loadTransactions() {
        String jsonData = LoadHelper.readFile("transactions.json");
        JSONObject jobj = new JSONObject(jsonData);
        JSONArray jarr = new JSONArray(jobj.getJSONArray("keywords").toString());
        List<Transaction> transactions = new ArrayList<Transaction>();
        return transactions;
    }
}
