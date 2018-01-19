package com.marino.quietstone.service;

import com.marino.quietstone.load.LoadRatesHelper;
import com.marino.quietstone.load.LoadTransactionsHelper;
import com.marino.quietstone.model.Product;
import com.marino.quietstone.model.ProductDetailsDTO;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.model.Transaction;
import com.marino.quietstone.util.Constants;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class to perform operations referred to Products
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class ProductsService {
    private List<Rate> rates;
    private List<Transaction> transactions;
    private Logger logger = Logger.getLogger(ProductsService.class.getSimpleName());

    /**
     * Constructor
     */
    public ProductsService() {
        LoadRatesHelper loadRatesHelper = new LoadRatesHelper();
        rates = loadRatesHelper.loadRates();
        LoadTransactionsHelper loadTransactionsHelper = new LoadTransactionsHelper();
        transactions = loadTransactionsHelper.loadTransactions();
    }

    /**
     * Returns the product list
     *
     * @return list of products description
     */
    public List<Product> getProductsList() {
        Map<String, Product> productMap = new HashMap<>();
        for (final Transaction transaction : transactions) {
            if (!productMap.containsKey(transaction.getProductId())) {
                Product newProduct = new Product();
                newProduct.setId(transaction.getProductId());
                newProduct.setName(transaction.getProductId());
                productMap.put(newProduct.getId(), newProduct);
            }
        }
        return new ArrayList(productMap.values());
    }

    /**
     * Returns the details of a Product
     *
     * @param productId the sku id of a product
     * @return product details
     * @see ProductDetailsDTO
     */
    public ProductDetailsDTO getProductDetails(final String productId) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        List<Transaction> productTransactions = new ArrayList<>();
        for (final Transaction transaction : transactions) {
            if (transaction.getProductId().equalsIgnoreCase(productId)) {
                productTransactions.add(transaction);
            }
        }
        productDetailsDTO.setProductId(productId);
        productDetailsDTO.setTransactions(productTransactions);

        double totalEur = 0.0;
        for (final Transaction transaction : productTransactions) {
            totalEur += getAmountIn(transaction.getAmount(), transaction.getCurrency(), Constants.CURRENCY_EUR);
        }
        productDetailsDTO.setTotal(totalEur);
        return productDetailsDTO;
    }

    /**
     * Transforms the amount from the currency 'currencyFrom' to the currency 'currencyTo'
     * If doesn exits the direct change rate, recursively calls itself until finds the destination.
     *
     * @param amount       amount to change
     * @param currencyFrom origin currency
     * @param currencyTo   destination currency
     * @return amount changed or zero
     */
    public double getAmountIn(final Double amount, final String currencyFrom, final String currencyTo) {
        double result = 0.00;
        if (currencyFrom != null) {
            Optional<Rate> toCurrencyTo = rates.stream().filter(rate -> rate.getFrom().equalsIgnoreCase(currencyFrom) && rate.getTo().equalsIgnoreCase(currencyTo)).findFirst();
            if (toCurrencyTo.isPresent()) {
                logger.log(Level.INFO, "Bingo! Direct rate found: {0}", toCurrencyTo.get());
                result = amount * toCurrencyTo.get().getRateFee();
            } else {
                Optional<Rate> toCurrencyTo2 = rates.stream().filter(rate -> rate.getFrom().equalsIgnoreCase(currencyFrom)).findFirst();
                double rateThru = 0;
                String newCurrencyFrom = null;
                if (toCurrencyTo2.isPresent()) {
                    rateThru = toCurrencyTo2.get().getRateFee();
                    newCurrencyFrom = toCurrencyTo2.get().getTo();

                }
                logger.log(Level.INFO, "No direct rate found, search again until find: {0} ", currencyTo);
                result = getAmountIn(amount * rateThru, newCurrencyFrom, currencyTo);
            }
        }
        return result;
    }
}
