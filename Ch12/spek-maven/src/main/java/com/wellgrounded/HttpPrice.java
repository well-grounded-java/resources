package com.wellgrounded;

import java.math.BigDecimal;

public class HttpPrice implements Price {
    @Override
    public BigDecimal getInitialPrice() {
        return HttpPricingService.getInitialPrice();
    }
}

