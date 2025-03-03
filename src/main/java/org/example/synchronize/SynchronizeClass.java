package org.example.synchronize;

public class SynchronizeClass {




    public static void main(String[] args) throws InterruptedException {

        SharedResource3 sharedResource = new SharedResource3();

        Runnable incrementTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 1 started");
                sharedResource.increment();

            }
        };
        Runnable decrementTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 1 started");
                sharedResource.decrement();

            }
        };

        Thread thread1 = new Thread(new SynchronizeBlock().decrementTask);
        Thread thread2 = new Thread(new SynchronizeBlock().decrementTask);

        for (int i = 0; i < 2000; i++) {
            thread1.run();
            thread2.run();
        }

        thread1.join();
        thread2.join();

        System.out.println("Final number is in Synchronized Class Example(It should be Zero) : " + sharedResource.getNumber());

    }




}

class SharedResource3 {

    private  static int number=4000;



    public int getNumber() {
        return number;
    }

    public static synchronized void increment() {
        System.out.println("Syncronized Increment  method started");
        number++;
    }

    public static synchronized void decrement() {
        System.out.println("Syncronized Decrement  method started");
        number--;


    }
}
