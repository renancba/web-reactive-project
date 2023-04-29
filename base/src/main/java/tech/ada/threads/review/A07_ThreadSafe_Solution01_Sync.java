package tech.ada.threads.review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class A07_ThreadSafe_Solution01_Sync {

    public static void main(String[] args) throws InterruptedException {
        final int TASKS = 5;
        A07_ThreadSafe_Solution01_Sync conta = new A07_ThreadSafe_Solution01_Sync();
        ExecutorService service = Executors.newFixedThreadPool(TASKS);
        try {
            for(int i = 0; i < TASKS; i++)
                service.submit(() -> conta.creditar(1d));
        } finally {
            service.shutdown();
            service.awaitTermination(1, TimeUnit.SECONDS);
        }

        System.out.println( "saldo [" + conta.saldo() + "]" );
    }

    Conta conta = new Conta();

    class Conta {
        public double saldo = 0;
    }

    public void creditar(double valor) {
        synchronized (this) {
            conta.saldo += valor;
            System.out.print((conta.saldo) + " ");
        }
    }

    public double saldo() {
        return conta.saldo;
    }

}