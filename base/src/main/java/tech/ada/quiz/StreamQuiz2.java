package tech.ada.quiz;

import java.util.Arrays;
import java.util.List;

public class StreamQuiz2 {

    public static void main(String[] args) {
        List<List<Integer>> numeros = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );
        int soma = numeros.stream()
                //.filter(n -> n % 1 == 1)
                .flatMap(List::stream)
                .reduce(0, Integer::sum);
        System.out.println(soma);
    }

}
