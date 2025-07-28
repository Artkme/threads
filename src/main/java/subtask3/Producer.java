package subtask3;

import java.util.Random;

public class Producer implements Runnable {
    private final MessageBus bus;
    private final String topic;
    private final Random random = new Random();

    public Producer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            String msg = "message - " + random.nextInt(100);
            bus.post(new Message(topic, msg));

            try {
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
