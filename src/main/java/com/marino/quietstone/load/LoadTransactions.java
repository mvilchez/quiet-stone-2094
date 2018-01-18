package com.marino.quietstone.load;

import com.marino.quietstone.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadTransactions {
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("transactions.json").getFile());
        try (FileReader reader = new FileReader(file)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) obj;

            //Iterate over transaction array
            transactionList.forEach(transaction -> {
                transactions.add(parseTransactionObject((JSONObject) transaction));
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private static Transaction parseTransactionObject(JSONObject transactionJson) {
        //Get transaction sku
        String sku = (String) transactionJson.get("sku");

        //Get transaction amount
        String amount = (String) transactionJson.get("amount");

        //Get transaction currency
        String currency = (String) transactionJson.get("currency");
        Transaction transaction = new Transaction();
        transaction.setProductId(sku);
        transaction.setAmount(Double.parseDouble(amount));
        transaction.setCurrency(currency);
        return transaction;
    }
}
