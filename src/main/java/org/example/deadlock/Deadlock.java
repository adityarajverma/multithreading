package org.example.deadlock;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {


        Pen pen= new Pen();
        Paper paper= new Paper();

        Thread t1 = new Thread(new Task1(paper, pen), "thread-01");
        Thread t2= new Thread(new Task2(paper, pen),"thread-02");

        t1.start();
        t2.start();

        System.out.println("Main thread Deadlock");


    }

}


class Task1 implements  Runnable
{
    Pen Pen;
    Paper Paper;

    public Task1(Paper paper, Pen pen)
    {
        this.Paper = paper;
        this.Pen = pen;
    }

    @Override
    public void run() {
        Pen.writeWithPenPaper(Paper);
    }
}


class Task2 implements Runnable
{
    Paper paper;
    Pen pen;

    public Task2(Paper paper, Pen pen)
    {
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
        paper.writeWithPaperPen(pen);
    }
}

class Pen
{


    public synchronized void getPen()
    {
        System.out.println("Getting Pen");
    }

    public synchronized void writeWithPenPaper(Paper paper)
    {
        paper.getPaper();
        System.out.println("Writing with pen and paper");

    }
}

class Paper
{
    public synchronized void getPaper()
    {
        System.out.println("Getting Paper");
    }

    public synchronized void writeWithPaperPen(Pen pen)
    {
        pen.getPen();
        System.out.println("Writing with paper and pen");

    }

}

