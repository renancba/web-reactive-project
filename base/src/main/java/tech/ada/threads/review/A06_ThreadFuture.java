package tech.ada.threads.review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A06_ThreadFuture {

    public static void main(String[] args) throws InterruptedException {
        // depois .. colocar thread.sleep e mostrar no jconsole
        Runnable runA = () -> System.out.println("task-a");
        Runnable runB = () -> {
            for (int i = 0; i < 4; i++) {
                System.out.println("task-b");
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(3);

        try {
            System.out.println("start");
            service.execute( runA );
            service.execute( runB );
            service.execute( runA );
            System.out.println("finish");
        } finally {
            service.shutdownNow(); // comentar depois e explicar porque nao para thread.main
        }

    }

}