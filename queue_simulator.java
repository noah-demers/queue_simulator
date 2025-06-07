// QueueSimulator.java
// Simple queue simulator using Java's LinkedList

import java.util.*;

public class QueueSimulator {
    private Queue<Customer> queue = new LinkedList<>();
    private int currentTime = 0;
    private Random random = new Random();

    public static void main(String[] args) {
        QueueSimulator sim = new QueueSimulator();
        sim.run(20); // Simulate for 20 time units
    }

    public void run(int totalTime) {
        for (currentTime = 0; currentTime < totalTime; currentTime++) {
            System.out.println("Time: " + currentTime);
            maybeAddCustomer();
            maybeServeCustomer();
            printQueue();
            System.out.println("----------------------");
            try { Thread.sleep(500); } catch (InterruptedException e) { }
        }
    }

    private void maybeAddCustomer() {
        // 50% chance to add a customer
        if (random.nextBoolean()) {
            Customer c = new Customer(currentTime);
            queue.add(c);
            System.out.println("Customer arrived at time " + currentTime);
        }
    }

    private void maybeServeCustomer() {
        // 30% chance to serve a customer
        if (!queue.isEmpty() && random.nextInt(100) < 30) {
            Customer c = queue.poll();
            System.out.println("Served customer who waited " + (currentTime - c.arrivalTime) + " units");
        }
    }

    private void printQueue() {
        System.out.println("Queue size: " + queue.size());
        if (!queue.isEmpty()) {
            System.out.print("Queue: ");
            for (Customer c : queue) {
                System.out.print("[Arrived: " + c.arrivalTime + "] ");
            }
            System.out.println();
        }
    }

    static class Customer {
        int arrivalTime;
        public Customer(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }
    }
}
