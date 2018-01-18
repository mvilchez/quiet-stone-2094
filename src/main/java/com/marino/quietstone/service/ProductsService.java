package com.marino.quietstone.service;

import com.marino.quietstone.load.LoadRates;
import com.marino.quietstone.load.LoadTransactions;
import com.marino.quietstone.model.Product;
import com.marino.quietstone.model.ProductDetailsDTO;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsService {
    private Map<String, Rate> rates;

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
    private ProductDetailsDTO getProductDetails(final String productId) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        List<Transaction> productTransactions = new ArrayList<Transaction>();
        LoadTransactions loadTransactions = new LoadTransactions();
        List<Transaction> transactions = loadTransactions.loadTransactions();
        for (final Transaction transaction : transactions) {
            if (transaction.getProductId().equalsIgnoreCase(productId)) {
                productTransactions.add(transaction);
            }
        }
        productDetailsDTO.setProductId(productId);
        productDetailsDTO.setTransactions(productTransactions);

        double totalEur = 0.0;
        for (final Transaction transaction : productTransactions) {
            totalEur += getAmountIn(transaction.getAmount(), transaction.getCurrency(), "EUR");
        }
        productDetailsDTO.setTotal(totalEur);
        return productDetailsDTO;
    }

    /**
     * @param amount
     * @param currencyFrom
     * @param currencyTo
     * @return
     */
    public double getAmountIn(final Double amount, final String currencyFrom, final String currencyTo) {
        double result;
        Rate toCurrencyTo = rates.get(currencyFrom);
        if (toCurrencyTo != null && toCurrencyTo.getTo().equalsIgnoreCase(currencyTo)) {
            result = amount * toCurrencyTo.getRate();
        } else {
            result = getAmountIn(amount, toCurrencyTo.getTo(), currencyTo);
        }
        return result;
    }
}
