package com.marino.quietstone.load;

import com.marino.quietstone.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper to load transactions from JSON file transactions.json
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class LoadTransactionsHelper {
    private Logger logger = Logger.getLogger(LoadTransactionsHelper.class.getSimpleName());

    /**
     * Converts a json object to a Transaction object
     *
     * @param transactionJson json for a transaction
     * @return a Transaction object
     */
    private static Transaction parseTransactionObject(final JSONObject transactionJson) {
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

    /**
     * Load transactions from a json file 'transactions.json'
     *
     * @return list of transactions
     */
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try {
            JSONUrlHelper jsonUrlHelper = new JSONUrlHelper();
            BufferedReader reader = jsonUrlHelper.getJSON("http://quiet-stone-2094.herokuapp.com/transactions");
            //JSONFileHelper JSONFileHelper = new JSONFileHelper();
            //BufferedReader reader = JSONFileHelper.getJSON("transactions.json");
            if (reader != null) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                JSONArray transactionList = (JSONArray) obj;

                //Iterate over transaction array
                transactionList.forEach(transaction ->
                        transactions.add(parseTransactionObject((JSONObject) transaction))
                );
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error IOException {0}", e);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error ParseException {0}", e);
        }
        return transactions;
    }
}
