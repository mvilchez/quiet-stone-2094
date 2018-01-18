package com.marino.quietstone.service;

import com.marino.quietstone.load.LoadRates;
import com.marino.quietstone.load.LoadTransactions;
import com.marino.quietstone.model.Product;
import com.marino.quietstone.model.ProductDetailsDTO;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.model.Transaction;

import java.util.*;

public class ProductsService {
    private List<Rate> rates;

    public ProductsService() {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
    }

    /**
     * Returns the product list
     *
     * @return list of products description
     */
    public List<Product> getProductsList() {
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
    public ProductDetailsDTO getProductDetails(final String productId) {
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
        Optional<Rate> toCurrencyTo = rates.stream().filter(rate -> rate.getFrom().equalsIgnoreCase(currencyFrom) && rate.getTo().equalsIgnoreCase(currencyTo)).findFirst();
        if (toCurrencyTo.isPresent()) {
            System.out.println("Bingo! encontrado tipo de cambio directo: "+toCurrencyTo.get());
            result = amount * toCurrencyTo.get().getRate();
        } else{
            Optional<Rate> toCurrencyTo2 = rates.stream().filter(rate -> rate.getFrom().equalsIgnoreCase(currencyFrom)).findFirst();
            System.out.println("No hay cambio directo, volvemos a buscar hasta encontrar: "+toCurrencyTo2.get());
            result = getAmountIn(amount* toCurrencyTo2.get().getRate(),toCurrencyTo2.get().getTo(), currencyTo);
        }
        return result;
    }
}
