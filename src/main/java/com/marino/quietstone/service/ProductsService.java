package com.marino.quietstone.service;

import com.marino.quietstone.load.LoadRates;
import com.marino.quietstone.load.LoadTransactions;
import com.marino.quietstone.model.Product;
import com.marino.quietstone.model.ProductDetails;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsService {
    private Map<String, Rate> rates = new HashMap<String, Rate>();

    public ProductsService() {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
    }

    /**
     * Returns the product list
     *
     * @return list of products description
     */
    private List<Product> getProductsList() {
        LoadTransactions loadTransactions = new LoadTransactions();
        List<Transaction> transactions = loadTransactions.loadTransactions();
        Map<String, Product> productMap = new HashMap<String, Product>();
        for (final Transaction transaction : transactions) {
            if (!productMap.containsKey(transaction.getProductId())) {
                Product newProduct = new Product();
                newProduct.setId(transaction.getProductId());
                newProduct.setName(transaction.getProductId());
            }
        }
        return new ArrayList(productMap.values());
    }

    /**
     * @param productId
     * @return producto details
     */
    private ProductDetails getProductDetails(final String productId) {
        ProductDetails productDetails = new ProductDetails();
        List<Transaction> productTransactions = new ArrayList<Transaction>();
        LoadTransactions loadTransactions = new LoadTransactions();
        List<Transaction> transactions = loadTransactions.loadTransactions();
        for (final Transaction transaction : transactions) {
            if (transaction.getProductId().equalsIgnoreCase(productId)) {
                productTransactions.add(transaction);
            }
        }

        double totalEur = 0.0;
        for (final Transaction transaction : productTransactions) {
            totalEur += toEur(transaction.getAmount(), transaction.getCurrency());
        }
        return productDetails;
    }

    /**
     * @param amount
     * @param currency
     * @return
     */
    private double toEur(final Double amount, final String currency) {
        double result;
        Rate toEuros = rates.get(currency);
        if (toEuros != null && toEuros.getTo().equalsIgnoreCase("EUR")) {
            result = amount * toEuros.getRate();
        } else if (toEuros.getTo().equalsIgnoreCase("EUR")){
            Rate derivated = get(amount, toEuros.getTo());
            result = amount * derivated.getRate();
        }
        return result;
    }
}
