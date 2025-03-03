package org.example.synchronize;

public class SynchronizeBlock {

    static SharedResource2 sharedResource = new SharedResource2(4000);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new SynchronizeBlock().decrementTask);
        Thread thread2 = new Thread(new SynchronizeBlock().decrementTask);

        for (int i = 0; i < 2000; i++) {
            thread1.run();
            thread2.run();
        }

        thread1.join();
        thread2.join();

        System.out.println("Final number is in Synchronized Block Example(It should be Zero) : " + sharedResource.getNumber());

    }


    Runnable incrementTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Task 1 started");

            synchronized (this) {
                sharedResource.increment();
            }
        }
    };
    Runnable decrementTask = new Runnable() {
        @Override
        public void run() {
            System.out.println("Task 1 started");

            synchronized (this) {
                sharedResource.decrement();
            }

        }
    };

}

class SharedResource2 {

    private int number;

    SharedResource2(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public synchronized void increment() {
        System.out.println("Syncronized Increment  method started");
        number++;
    }

    public void decrement() {
        System.out.println("Syncronized Decrement  method started");
        synchronized (this) {
            number--;

        }

    }
}
