package com.wellgrounded;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ShowTest {
    @Test
    public void plentyOfSpace() {
        var db = new FakeTicketDatabase();
        var show = new Show(db, 5);

        var name = "New One";
        show.addTicket(name, BigDecimal.ONE);

        var mine = db.findByName(name);
        assertEquals(name, mine.getClientName());
        assertEquals(BigDecimal.ONE, mine.getPrice());
    }
}