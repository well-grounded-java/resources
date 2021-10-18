package com.wellgrounded;

import java.math.BigDecimal;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TicketRevenueTest {
    private TicketRevenue venueRevenue;
    private BigDecimal expectedRevenue;

    @Before
    public void setUp() {
        venueRevenue = new TicketRevenue();
    }

    @Test(expected=IllegalArgumentException.class)
    public void failIfLessThanZeroTicketsAreSold() {
        venueRevenue.estimateTotalRevenue(-1);
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

    @Test(expected=IllegalArgumentException.class)
    public void failIfMoreThanOneHundredTicketsAreSold() {
        venueRevenue.estimateTotalRevenue(101);
    }
}