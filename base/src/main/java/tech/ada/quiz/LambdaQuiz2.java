package tech.ada.quiz;

import java.util.Arrays;

public class LambdaQuiz2 {

    public static void main(String[] args) {
        String[] nomes = {"d", "a", "b", "c"};
        Arrays.stream(nomes)
            .sorted((a, b) -> b.compareTo(a))
            .forEach( (nome) -> System.out.print(nome + " ") );
    }

}