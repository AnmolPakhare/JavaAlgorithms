package com.practise;

import java.util.TreeMap;

public class CalendarBooking {
    private TreeMap<Integer, Integer> bookings;

    public CalendarBooking() {
        bookings = new TreeMap<>();
    }

    public boolean bookStage(int start, int end) {
        if (start >= end) return false;

        // Find the previous event (if any)
        Integer prev = bookings.floorKey(start);
        if (prev != null && bookings.get(prev) > start) {
            return false; // Overlaps with previous event
        }

        // Find the next event (if any)
        Integer next = bookings.ceilingKey(start);
        if (next != null && next < end) {
            return false; // Overlaps with next event
        }

        // No overlap, book the event
        bookings.put(start, end);
        return true;
    }

    public static void main(String[] args) {
        CalendarBooking calendar = new CalendarBooking();

        System.out.println(calendar.bookStage(10, 20)); // true
        System.out.println(calendar.bookStage(15, 25)); // false (overlaps)
        System.out.println(calendar.bookStage(20, 30)); // true
        System.out.println(calendar.bookStage(5, 10));  // true
        System.out.println(calendar.bookStage(25, 30));  // false8
    }
}
