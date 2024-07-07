package com.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FestivalStatisticsThreadTest {

    @Test
    public void testGenerateStatistics() throws InterruptedException {
        FestivalGate gate = new FestivalGate();
        gate.addTicket(TicketType.FULL);
        gate.addTicket(TicketType.FULL_VIP);
        gate.addTicket(TicketType.FREE_PASS);
        gate.addTicket(TicketType.ONE_DAY);
        gate.addTicket(TicketType.ONE_DAY_VIP);

        FestivalStatisticsThread statisticsThread = new FestivalStatisticsThread(gate);
        statisticsThread.start();

        // Let the statistics thread run once
        Thread.sleep(6000);

        BlockingQueue<TicketType> queue = gate.getQueue();
        assertEquals(5, queue.size());

        // Clear the queue to ensure correct counting in the next statistics generation
        queue.clear();
        gate.addTicket(TicketType.FULL);
        gate.addTicket(TicketType.FULL);
        gate.addTicket(TicketType.FREE_PASS);

        // Let the statistics thread run again
        Thread.sleep(6000);

        assertEquals(3, queue.size());
        assertEquals(TicketType.FULL, queue.poll());
        assertEquals(TicketType.FULL, queue.poll());
        assertEquals(TicketType.FREE_PASS, queue.poll());
    }
}
