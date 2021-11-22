package com.wellgrounded;

import java.math.BigDecimal;

public class TicketRevenue {
    private final static int TICKET_PRICE = 30;

    public BigDecimal estimateTotalRevenue(int numberOfTicketsSold)
            throws IllegalArgumentException {

        if (numberOfTicketsSold < 0 || numberOfTicketsSold > 100) {
            throw new IllegalArgumentException("# Tix sold must == 1..100");
        }

        return new BigDecimal(TICKET_PRICE * numberOfTicketsSold);
    }
}
