package tech.ada.quiz;

import java.util.Arrays;

public class LambdaQuiz1 {

    public static void main(String[] args) {
        Runnable run = () -> { System.out.println("hello"); };
        run.run();
        Arrays.stream( new int[] {1,2,3} )
            .forEach( (n) -> System.out.print(n + " ") );

    }

}