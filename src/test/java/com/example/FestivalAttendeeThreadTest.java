package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FestivalAttendeeThreadTest {

    @Test
    public void testRun() throws InterruptedException {
        FestivalGate gate = new FestivalGate();
        TicketType ticketType = TicketType.FULL;

        FestivalAttendeeThread attendeeThread = new FestivalAttendeeThread(ticketType, gate);
        attendeeThread.start();
        attendeeThread.join();

        assertEquals(1, gate.getQueue().size());
        assertEquals(TicketType.FULL, gate.getQueue().peek());
    }
}
