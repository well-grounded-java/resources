package com.wellgrounded;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketRevenueTest {
    private TicketRevenue venueRevenue;
    private BigDecimal expectedRevenue;

    @BeforeEach
    public void setUp() {
        venueRevenue = new TicketRevenue();
    }

    @Test
    public void failIfLessThanZeroTicketsAreSold() {
        assertThrows(IllegalArgumentException.class,
                     () -> venueRevenue.estimateTotalRevenue(-1));
    }

    @Test
    public void zeroSalesEqualsZeroRevenue() {
        assertEquals(BigDecimal.ZERO, venueRevenue.estimateTotalRevenue(0));
    }

    @Test
    public void oneTicketSoldIsThirtyInRevenue() {
        expectedRevenue = new BigDecimal("30");
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(1));
    }

    @Test
    public void tenTicketsSoldIsThreeHundredInRevenue() {
        expectedRevenue = new BigDecimal("300");
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(10));
    }

    @Test
    public void failIfMoreThanOneHundredTicketsAreSold() {
        assertThrows(IllegalArgumentException.class,
                () -> venueRevenue.estimateTotalRevenue(101));
    }
}