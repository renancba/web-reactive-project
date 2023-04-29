package tech.ada.threads.review;

public class A03_ThreadPause {

    public static void main(String[] args) {
        var task = new Thread(() -> pause());
        task.setDaemon(true); // o que ocorre se colocar apos start?
        task.start();
        System.out.println( "fim" );
    }

    private static void pause() {
        try {
            System.out.println("a");
            Thread.sleep(3_000);
            System.out.println("b");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}