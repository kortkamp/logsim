package com.kortkamp.simlog.events;

import java.util.List;
import java.util.PriorityQueue;

public class EventManager {

    private PriorityQueue<Event> queue = new PriorityQueue<>();

    public void schedule(Event event) {
        queue.add(event);
    }

    public void scheduleMany(List<? extends Event> event) {
        queue.addAll(event);
    }

    public Event nextEvent() {
        return queue.poll();
    }

    public boolean hasEvents() {
        return !queue.isEmpty();
    }
}
