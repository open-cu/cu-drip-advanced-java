package ru.centraluniversity.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class EventPool {

    private final BlockingQueue<Event> queue;

    public EventPool(int size) {
        this.queue = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            queue.add(new Event(0, 0, 0));
        }
    }


    public Event borrowEvent() throws InterruptedException {
        return queue.take();
    }

    public void returnEvent(Event event) {
        event.reset(); // Очищаем состояние
        queue.offer(event);
    }
}
