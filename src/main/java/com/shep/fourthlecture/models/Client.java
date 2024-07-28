package com.shep.fourthlecture.models;

import com.shep.fourthlecture.models.base.User;

public class Client extends User {

    public Client(int id, String name) {
        super(id, "Client", name);
    }

    public void getTicket(Ticket ticket) {
        System.out.println("Client " + super.getName() + " gets a ticket: " + ticket.toString());
    }

    @Override
    public void uniqueFunction(Ticket ticket) {
        getTicket(ticket);
    }
}
