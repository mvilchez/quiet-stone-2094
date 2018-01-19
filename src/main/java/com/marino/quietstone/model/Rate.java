package com.marino.quietstone.model;

/**
 * Class Rate
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class Rate {
    private String from;
    private String to;
    private Double rateFee;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getRateFee() {
        return rateFee;
    }

    public void setRateFee(Double rateFee) {
        this.rateFee = rateFee;
    }


    @Override
    public String toString() {
        return "Rate{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rateFee=" + rateFee +
                '}';
    }
}
