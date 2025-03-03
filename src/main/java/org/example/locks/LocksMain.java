package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(()->
        {
            for( int i=0;i<200;i++)
            {
                counter.increment();
            }
        });

        Thread t2= new Thread(()->
        {
            for( int i=0;i<200;i++)
            {
                counter.increment();
            }
        });

        System.out.println("Initial count: "+counter.getCount());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: "+counter.getCount());

    }

}

class Counter {
    private int count = 0;

    private Lock lock = new ReentrantLock();

    public void increment() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }

    }

    public int getCount() {
        return count;
    }
}
