package com.wellgrounded;

import redis.clients.jedis.Jedis;

import java.math.BigDecimal;

public class CachedPrice implements Price {
    private final Price priceLookup;
    private final Jedis cacheClient;

    private static final String priceKey = "price";

    CachedPrice(Price priceLookup, Jedis cacheClient) {
        this.priceLookup = priceLookup;
        this.cacheClient = cacheClient;
    }

    @Override
    public BigDecimal getInitialPrice() {
        String cachedPrice = cacheClient.get(priceKey);
        if (cachedPrice != null) {
            return new BigDecimal(cachedPrice);
        }

        BigDecimal price = priceLookup.getInitialPrice();
        cacheClient.set(priceKey, price.toPlainString());
        return price;
    }
}
