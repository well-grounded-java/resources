package com.wellgrounded

import java.math.BigDecimal

class StubPrice : Price {
    override fun getInitialPrice(): BigDecimal {
        return BigDecimal("10")
    }
}