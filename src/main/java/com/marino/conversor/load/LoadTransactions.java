package com.marino.conversor.load;

import com.marino.conversor.model.Product;
import com.marino.conversor.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LoadTransactions {
    private List<Transaction> loadProducts() {
        String jsonData = LoadHelper.readFile("transactions.json");
        JSONObject jobj = new JSONObject(jsonData);
        JSONArray jarr = new JSONArray(jobj.getJSONArray("keywords").toString());
        List<Transaction> transactions = new ArrayList<Transaction>();
        return transactions;
    }
}
