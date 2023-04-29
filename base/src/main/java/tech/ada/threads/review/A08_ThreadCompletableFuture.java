package tech.ada.threads.review;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class A08_ThreadCompletableFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executando operação 1 em Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Operação 1 finalizada.";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executando operação 2 em Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Operação 2 finalizada.";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executando operação 3 em Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Operação 3 finalizada.";
        });

        System.out.println("a");
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

        System.out.println("b");
        allFutures.get(); // aguarda todas as operações serem finalizadas

        System.out.println("c");
        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
    }

}
