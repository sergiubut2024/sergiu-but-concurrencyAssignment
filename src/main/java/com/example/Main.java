package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FestivalGate gate = new FestivalGate();

        List<Thread> attendeeThreads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            TicketType ticketType = TicketType.getRandomTicketType();
            FestivalAttendeeThread attendeeThread = new FestivalAttendeeThread(ticketType, gate);
            attendeeThreads.add(attendeeThread);
        }

        FestivalStatisticsThread statisticsThread = new FestivalStatisticsThread(gate);
        statisticsThread.start();

        for (Thread thread : attendeeThreads) {
            thread.start();
        }

        // Optional: join all attendee threads to ensure they finish before the main thread ends
        for (Thread thread : attendeeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
