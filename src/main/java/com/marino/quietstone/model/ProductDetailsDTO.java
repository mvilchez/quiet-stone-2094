package com.marino.quietstone.model;

import java.util.List;

/**
 * DTO class for details of a product
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class ProductDetailsDTO {
    private String productId;
    private List<Transaction> transactions;
    private double total;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
