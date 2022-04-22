package com.wellgrounded

import org.junit.jupiter.api.Assertions.*
import org.spekframework.spek2.Spek

import java.math.BigDecimal

object InMemoryCachedPriceSpek : Spek({
    val stubbedPrice : Price by memoized { StubPrice() }

    group("empty cache") {
        val cachedPrice by memoized { InMemoryCachedPrice(stubbedPrice) }

        test("gets default value") {
            assertEquals(BigDecimal(10), cachedPrice.initialPrice)
        }

        test("returns exact same object") {
            val first = cachedPrice.initialPrice
            val second = cachedPrice.initialPrice
            assertTrue(first === second)
        }

        listOf(1, 2, 3).forEach {
            test("testing $it") {
                assertNotEquals(BigDecimal(it), cachedPrice.initialPrice)
            }
        }
    }

    //group("existing cache") {
    //    lateinit var cachedPrice : InMemoryCachedPrice

    //    beforeEachTest {
    //        cachedPrice = InMemoryCachedPrice(stubbedPrice, BigDecimal(20))
    //    }

    //    test("gets cached value") {
    //        assertEquals(BigDecimal(20), cachedPrice.initialPrice)
    //    }
    //}
})
