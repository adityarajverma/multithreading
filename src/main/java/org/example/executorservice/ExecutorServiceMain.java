package org.example.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceMain {

    public static void main(String[] args) {
        ExecutorService executorService=  Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executorService.submit(()->{
                System.out.println("Thread Name: "+Thread.currentThread().getName());
            });

        }
        executorService.shutdown();
    }
}


