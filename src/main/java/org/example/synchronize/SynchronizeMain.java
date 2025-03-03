package org.example.synchronize;

public class SynchronizeMain {

     static SharedResource1 sharedResource = new SharedResource1(4000);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1= new Thread(new SynchronizeMain().decrementTask);
        Thread thread2= new Thread(new SynchronizeMain().decrementTask);

        for (int i =0; i<2000;i++){
            thread1.run();
            thread2.run();
        }

        thread1.join();
        thread2.join();

        System.out.println("Final number is : "+sharedResource.getNumber());

    }


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

}

class SharedResource1 {

    private int number;

    SharedResource1(int number) {
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
