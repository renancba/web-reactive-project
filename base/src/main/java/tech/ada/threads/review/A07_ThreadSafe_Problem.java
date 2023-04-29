package tech.ada.threads.review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A07_ThreadSafe_Problem {

    public static void main(String[] args) throws InterruptedException {
        final int TASKS = 5;
        A07_ThreadSafe_Problem conta = new A07_ThreadSafe_Problem();
        ExecutorService service = Executors.newFixedThreadPool(TASKS);
        try {
            for (int i = 0; i < TASKS; i++)
                service.submit(() -> conta.creditar(1));
        } finally {
            service.shutdown();
        }

        System.out.println("saldo [" + conta.saldo() + "]");
    }

    Conta conta = new Conta();

    class Conta {
        public int saldo = 0;
    }

    public void creditar(int valor) {
        conta.saldo += valor;
        System.out.print((conta.saldo) + " ");
    }

    public int saldo() {
        return conta.saldo;
    }

}