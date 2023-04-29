package tech.ada.threads.review;

public class A04_ThreadMessageSleep {

    private static boolean message = false;

    public static void main(String[] args) throws InterruptedException {

        final var mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(3_000);
                message = true;
                mainThread.interrupt(); // colocar depois de rodar - exemplo fallback
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        while (!message) {
            System.out.println("enviando msg ..");
            Thread.sleep(500);
        }

        System.out.println("enviada!!!");

    }

}