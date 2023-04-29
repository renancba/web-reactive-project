package tech.ada.threads.review;

public class A01_FirstThread {

    public static void main(String[] args) {
        new Thread( () -> System.out.println( "hello" )).start();
        System.out.println( "world" );
    }

}