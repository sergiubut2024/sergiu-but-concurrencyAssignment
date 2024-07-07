package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FestivalGate {
    private final BlockingQueue<TicketType> queue = new LinkedBlockingQueue<>();

    public void addTicket(TicketType ticketType) {
        queue.add(ticketType);
    }

    public BlockingQueue<TicketType> getQueue() {
        return queue;
    }
}
