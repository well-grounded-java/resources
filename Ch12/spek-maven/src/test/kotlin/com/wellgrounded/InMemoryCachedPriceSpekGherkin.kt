package com.wellgrounded

import org.spekframework.spek2.Spek

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.style.gherkin.Feature
import java.math.BigDecimal

object InMemoryCachedPriceSpekGherkin : Spek({
    Feature("caching") {
        val stubbedPrice by memoized { StubPrice() }

        lateinit var cachedPrice : Price
        lateinit var result : BigDecimal

        Scenario("empty cache") {
            Given("an empty cache") {
                cachedPrice = InMemoryCachedPrice(stubbedPrice)
            }

            When("calculating") {
                result = cachedPrice.initialPrice
            }

            Then("it looks up from original source") {
                assertEquals(BigDecimal(10), result)
            }

            Then("it sees same reference late") {
                assertEquals(true, result === cachedPrice.initialPrice)
            }
        }

        Scenario("cached value") {
            lateinit var cachedPrice : Price
            lateinit var result : BigDecimal

            Given("a cached price") {
                cachedPrice = InMemoryCachedPrice(stubbedPrice, BigDecimal(20))
            }

            When("calculating") {
                result = cachedPrice.initialPrice
            }

            Then("it finds in the cache") {
                assertEquals(BigDecimal(20), result)
            }
        }
    }
})
