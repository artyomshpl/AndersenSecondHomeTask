package com.shep.secondlecture.services;

import com.shep.secondlecture.models.Ticket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private List<Ticket> tickets = new ArrayList<>();

    public void saveTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void saveTicket(String concertHall, String eventCode, Long time) {
        saveTicket(new Ticket(concertHall, eventCode, time));
    }

    public void saveTicket(String id, String concertHall, String eventCode, Long time, boolean isPromo, char stadiumSector, double maxBackpackWeight, double price) {
        saveTicket(new Ticket(id, concertHall, eventCode, time, isPromo, stadiumSector, maxBackpackWeight, price));
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();

        long unixTimestamp = Instant.now().getEpochSecond();
        long unixTimestampOtherTime = Instant.now().plus(Duration.ofDays(1)).plus(Duration.ofHours(2)).getEpochSecond();

        //Saving tickets in different ways (Saving not using JPA)
        ticketService.saveTicket(new Ticket());
        ticketService.saveTicket("Hall A", "123", unixTimestamp);
        ticketService.saveTicket("Hall B", "789", unixTimestampOtherTime);
        ticketService.saveTicket(new Ticket("Hall C", "012", unixTimestampOtherTime));
        ticketService.saveTicket("789", "Hall D", "456", unixTimestampOtherTime, false, 'C', 5.0, 7.0);


        // Print all tickets
        for (Ticket ticket : ticketService.getTickets()) {
            System.out.println(ticket);
        }
    }
}
