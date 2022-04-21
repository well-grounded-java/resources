package com.wellgrounded

import org.spekframework.spek2.Spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.math.BigDecimal

object InMemoryCachedPriceSpek : Spek({
    val stubbedPrice by memoized { StubPrice() }

    group("empty cache") {
        val cachedPrice by memoized { InMemoryCachedPrice(stubbedPrice) }

        test("gets default value") {
            assertEquals(BigDecimal(10), cachedPrice.initialPrice)
        }

        test("gets same value when called again") {
            val first = cachedPrice.initialPrice
            val second = cachedPrice.initialPrice
            assertTrue(first === second)
        }
    }

    group("existing cache") {
        lateinit var cachedPrice : InMemoryCachedPrice

        beforeEachTest {
            cachedPrice = InMemoryCachedPrice(stubbedPrice, BigDecimal(20))
        }

        test("gets cached value") {
            assertEquals(BigDecimal(20), cachedPrice.initialPrice)
        }
    }
})
