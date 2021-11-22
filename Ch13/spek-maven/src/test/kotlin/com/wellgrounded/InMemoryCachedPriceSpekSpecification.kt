package com.wellgrounded

import org.spekframework.spek2.Spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.style.gherkin.Feature
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

object InMemoryCachedPriceSpekSpecification : Spek({
    describe("caching") {
        val stubbedPrice by memoized { StubPrice() }

        describe("empty cache") {
            val cachedPrice by memoized { InMemoryCachedPrice(stubbedPrice) }

            it("looks up the value") {
                assertEquals(BigDecimal(10), cachedPrice.initialPrice)
            }
        }

        describe("cached value") {
            val cachedPrice by memoized { InMemoryCachedPrice(stubbedPrice, BigDecimal(20)) }

            it("looks up the value") {
                assertEquals(BigDecimal(20), cachedPrice.initialPrice)
            }
        }
    }
})