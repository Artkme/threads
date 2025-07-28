package subtask3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageBus {
    private final Map<String, List<Message>> topicQueues = new HashMap<>();
    private final Map<String, Object> topicLocks = new HashMap<>();

    public void post(Message message) {
        Object lock = getLock(message.getTopic());
        synchronized (lock) {
            synchronized (topicQueues) {
                topicQueues.computeIfAbsent(message.getTopic(), k -> new ArrayList<>());
            }
            topicQueues.get(message.getTopic()).add(message);
            lock.notifyAll();
        }
    }

    public Message consume(String topic) throws InterruptedException {
        Object lock = getLock(topic);
        synchronized (lock) {
            while (!hasMessage(topic)) {
                lock.wait();
            }
            return topicQueues.get(topic).remove(0);
        }
    }

    private boolean hasMessage(String topic) {
        List<Message> queue = topicQueues.get(topic);
        return queue != null && !queue.isEmpty();
    }

    private Object getLock(String topic) {
        synchronized (topicLocks) {
            return topicLocks.computeIfAbsent(topic, k -> new Object());
        }
    }
}
