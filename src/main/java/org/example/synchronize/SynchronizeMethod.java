package org.example.synchronize;

public class SynchronizeMethod {
    public static void main(String[] args) throws InterruptedException {

        SharedResource4 sharedResource = new org.example.synchronize.SharedResource4();

        Runnable incrementTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Increment Task is started");
                for (int i = 0; i < 2000; i++) {
                    sharedResource.increment();
                }

            }
        };
        Runnable decrementTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Decrement Task is started");
                for (int i = 0; i < 2000; i++) {
                    sharedResource.decrement();
                }

            }
        };

        Thread thread1 = new Thread(new SynchronizeBlock().decrementTask);
        Thread thread2 = new Thread(new SynchronizeBlock().decrementTask);


        thread1.start();
        thread2.start();



        thread1.join();
        thread2.join();

        System.out.println("Final number is in Synchronized method Example(It should be Zero) : " + sharedResource.getNumber());

    }


}

class SharedResource4 {

    private int number = 4000;


    public int getNumber() {
        return number;
    }

    public synchronized void increment() {
        System.out.println("Syncronized Increment  method started");
        number++;
    }

    public synchronized void decrement() {
        System.out.println("Syncronized Decrement  method started");
        number--;


    }
}


