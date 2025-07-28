package subtask2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Object lock = new Object();

        Runnable writerTask = () -> {
            Random random = new Random();
            while (true) {
                int number = random.nextInt(100);
                synchronized (lock) {
                    numbers.add(number);
                    lock.notifyAll();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable sumPrinterTask = () -> {
            int lastProcessed = 0;
            while (true) {
                synchronized (lock) {
                    while (lastProcessed >= numbers.size()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    int sum = 0;
                    for (int num : numbers) {
                        sum += num;
                    }
                    System.out.println("Sum: " + sum);
                    lastProcessed = numbers.size();
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable sqrtPrinterTask = () -> {
            int lastProcessed = 0;
            while (true) {
                synchronized (lock) {
                    while (lastProcessed >= numbers.size()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    long sumOfSquares = 0;
                    for (int num : numbers) {
                        sumOfSquares += (long) num * num;
                    }

                    double result = Math.sqrt(sumOfSquares);
                    System.out.printf("Sqrt of sum of squares: %.2f%n", result);
                    lastProcessed = numbers.size();
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread writerThread = new Thread(writerTask, "Writer");
        Thread sumPrinterThread = new Thread(sumPrinterTask, "SumPrinter");
        Thread sqrtPrinterThread = new Thread(sqrtPrinterTask, "SqrtPrinter");

        writerThread.start();
        sumPrinterThread.start();
        sqrtPrinterThread.start();
    }
}
