package com.wellgrounded;

import java.math.BigDecimal;

public class Ticket {
    public static final int BASIC_TICKET_PRICE = 30;

    private BigDecimal faceValue = null;

    private final String clientName;
    private final Price priceSource;
    private final BigDecimal discountRate;

    private final class FixedPrice implements Price {
        private BigDecimal initialPrice;

        FixedPrice() {
            this(new BigDecimal(BASIC_TICKET_PRICE));
        }

        FixedPrice(BigDecimal initialPrice) {
            this.initialPrice = initialPrice;
        }

        public BigDecimal getInitialPrice() {
            return initialPrice;
        }
    }

    public Ticket(String clientName) {
        this.clientName = clientName;
        this.priceSource = new FixedPrice();
        this.discountRate = new BigDecimal("1.0");
    }

    public Ticket(String clientName, BigDecimal price) {
        this.clientName = clientName;
        this.priceSource = new FixedPrice(price);
        this.discountRate = new BigDecimal("1.0");
    }

    public Ticket(Price sourcePrice) {
        this.clientName = "Missing name";
        this.priceSource = sourcePrice;
        this.discountRate = new BigDecimal("1.0");
    }

    public Ticket(Price sourcePrice, BigDecimal discountRate) {
        this.clientName = "Missing name";
        this.priceSource = sourcePrice;
        this.discountRate = discountRate;
    }

    public String getClientName() {
        return clientName;
    }

    public BigDecimal getPrice() {
        return priceSource.getInitialPrice();
    }

    public BigDecimal getDiscountPrice() {
        if (faceValue == null) {
            faceValue = priceSource.getInitialPrice();
        }

        return faceValue.multiply(discountRate);
    }
}