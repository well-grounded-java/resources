package com.wellgrounded;

import java.math.BigDecimal;

public class InMemoryCachedPrice implements Price {
    private final Price priceLookup;

    private BigDecimal cached;

    InMemoryCachedPrice(Price priceLookup) {
        this(priceLookup, null);
    }

    InMemoryCachedPrice(Price priceLookup, BigDecimal cached) {
        this.priceLookup = priceLookup;
        this.cached = cached;
    }

    @Override
    public BigDecimal getInitialPrice() {
        if (cached == null) {
            cached = priceLookup.getInitialPrice();
        }

        return cached;
    }

    void clearCache() {
        cached = null;
    }
}