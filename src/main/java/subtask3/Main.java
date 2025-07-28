package subtask3;

public class Main {
    public static void main(String[] args) {
        MessageBus bus = new MessageBus();

        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(bus, "sms"), "SMS-Producer-" + i).start();
            new Thread(new Producer(bus, "email"), "EMAIL-Producer-" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(bus, "sms"), "SMS-Consumer-" + i).start();
            new Thread(new Consumer(bus, "email"), "EMAIL-Consumer-" + i).start();
        }
    }
}
