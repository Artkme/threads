package subtask3;

public class Consumer implements Runnable {
    private final MessageBus bus;
    private final String topic;

    public Consumer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = bus.consume(topic);
                System.out.printf("[%s] %s -> %s%n", Thread.currentThread().getName(), topic.toUpperCase(), msg.getPayload());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
