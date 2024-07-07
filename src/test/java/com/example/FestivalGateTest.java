package com.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FestivalGateTest {

    @Test
    public void testAddTicket() {
        FestivalGate gate = new FestivalGate();
        gate.addTicket(TicketType.FULL);

        BlockingQueue<TicketType> queue = gate.getQueue();
        assertEquals(1, queue.size());
        assertEquals(TicketType.FULL, queue.peek());
    }
}
