package com.urise.webapp.storage.deadlock;

public class MainDeadlock {

    String x = "abc";
    String y = "efg";

    Thread thread1 = new Thread() {
        @Override
        public void run() {
            synchronized (x) {
                System.out.println("hello1");
                threadMethod();
                synchronized (y) {
                    System.out.println("bye1");

                }
            }
        }
    };

    Thread thread2 = new Thread() {
        @Override
        public void run() {
            synchronized (y) {
                System.out.println("hello2");
                threadMethod();
                synchronized (x) {
                    System.out.println("bye2");

                }
            }
        }
    };

    public void threadMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MainDeadlock m = new MainDeadlock();
        m.thread1.start();
        m.thread2.start();
    }
}
