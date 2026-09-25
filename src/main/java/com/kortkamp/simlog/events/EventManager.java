package com.kortkamp.simlog.events;

import java.util.List;
import java.util.PriorityQueue;

public class EventManager {

    public static volatile EventManager instance;

    private PriorityQueue<Event> queue;

    private EventManager() {
        this.queue = new PriorityQueue<>();
    }

        // Singleton holder
    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

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
