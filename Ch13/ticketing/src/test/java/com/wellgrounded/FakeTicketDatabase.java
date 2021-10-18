package com.wellgrounded;

import java.util.HashMap;

class FakeTicketDatabase implements TicketDatabase {
    private HashMap<Integer, Ticket> tickets = new HashMap<>();
    private Integer nextId = 1;

    @Override
    public Ticket findByName(String name) {
        var found = tickets.values()
                .stream()
                .filter(ticket -> ticket.getClientName().equals(name))
                .findFirst();
        return found.orElse(null);
    }

    @Override
    public int count() {
        return tickets.size();
    }

    @Override
    public void insert(Ticket ticket) {
        tickets.put(nextId, ticket);
        nextId++;
    }

    @Override
    public Ticket findById(int id) {
        return tickets.get(id);
    }

    @Override
    public void delete(int id) {
        tickets.remove(id);
    }
}
