package com.example;

import java.util.concurrent.BlockingQueue;

public class FestivalStatisticsThread extends Thread {
    private final FestivalGate gate;

    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
                BlockingQueue<TicketType> queue = gate.getQueue();
                if (!queue.isEmpty()) {
                    generateStatistics(queue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateStatistics(BlockingQueue<TicketType> queue) {
        int full = 0, fullVip = 0, freePass = 0, oneDay = 0, oneDayVip = 0;

        for (TicketType ticketType : queue) {
            switch (ticketType) {
                case FULL -> full++;
                case FULL_VIP -> fullVip++;
                case FREE_PASS -> freePass++;
                case ONE_DAY -> oneDay++;
                case ONE_DAY_VIP -> oneDayVip++;
            }
        }

        System.out.println("Total people entered: " + queue.size());
        System.out.println("Full tickets: " + full);
        System.out.println("Full VIP tickets: " + fullVip);
        System.out.println("Free passes: " + freePass);
        System.out.println("One-day tickets: " + oneDay);
        System.out.println("One-day VIP tickets: " + oneDayVip);
    }
}
