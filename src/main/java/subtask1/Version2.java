package subtask1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Version2 {
    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<Integer, Integer>());

        Runnable r1 = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.put(i, 1);
                }
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                int sum = 0;
                synchronized (map) {
                    for (int val : map.values()) {
                        sum += val;
                    }
                }
                System.out.println("Thread2: Sum is " + sum);
            }
        };

        long startTime = System.nanoTime();

        Thread t1 = new Thread(r1, "Writer");
        Thread t2 = new Thread(r2, "Reader");
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        long endTime = System.nanoTime();
        long durationInNano = endTime - startTime;
        double durationInMillis = durationInNano / 1000000.0;

        System.out.printf("Execution time: %.3f ms%n", durationInMillis);
    }
}
