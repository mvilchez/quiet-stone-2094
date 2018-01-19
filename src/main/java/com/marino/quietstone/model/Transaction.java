package com.marino.quietstone.model;

/**
 * Class Transaction
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class Transaction {
    private String productId;
    private Double amount;
    private String currency;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
