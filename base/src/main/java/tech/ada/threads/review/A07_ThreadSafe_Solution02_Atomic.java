package tech.ada.threads.review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class A07_ThreadSafe_Solution02_Atomic {

    public static void main(String[] args) throws InterruptedException {
        final int TASKS = 5;
        A07_ThreadSafe_Solution02_Atomic conta = new A07_ThreadSafe_Solution02_Atomic();
        ExecutorService service = Executors.newFixedThreadPool(TASKS);
        try {
            for (int i = 0; i < TASKS; i++)
                service.submit(() -> conta.creditar(1));
        } finally {
            service.shutdown();
            service.awaitTermination(1, TimeUnit.SECONDS);
        }

        System.out.println("saldo [" + conta.saldo() + "]");
    }

    Conta conta = new Conta();

    class Conta {
        public AtomicInteger saldo = new AtomicInteger(0);
        // falar depois .. poderia usar o AtomicReference<Double> mas nao tem addAndGet
    }

    public void creditar(int valor) {
        System.out.print(conta.saldo.addAndGet(valor) + " ");
    }

    public double saldo() {
        return conta.saldo.get();
    }

}