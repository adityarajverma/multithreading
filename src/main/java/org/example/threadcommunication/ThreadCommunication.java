package org.example.threadcommunication;

public class ThreadCommunication {

    private Message message;

    public static void main(String[] args) {
        Message message = new Message();
        Thread producer = new Thread(new Producer(message));
        Thread consumer = new Thread(new Consumer(message));

        producer.start();
        consumer.start();
    }
}


class Message {

    private int data;
    private boolean hasMessage;


    public synchronized void produce(int input) {
        while (hasMessage) {
            try {
                wait();           // wait for consumer to consume the message
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        this.data = input;
        System.out.println("Produced: " + input);
        hasMessage = true;
        notify(); // notify consumer that message is ready
    }

    public synchronized void consume() {
        while (!hasMessage) {
            try {
                wait(); // wait for producer to produce the message
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed: " + data);
        hasMessage = false;
        notify(); // notify producer that message is consumed

    }
}


class Producer implements  Runnable
{

    private Message message;

    Producer(Message message)
    {
        this.message = message;
    }

    public void run() {
        for(int i=0; i<10; i++) {
            message.produce(i);
        }
    }

}

class Consumer implements  Runnable
{
    private Message message;

    Consumer(Message message)
    {
        this.message = message;
    }


    public void run() {
        for(int i=0; i<10; i++) {
            message.consume();
        }

    }
}
