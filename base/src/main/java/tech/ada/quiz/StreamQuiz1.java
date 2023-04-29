package tech.ada.quiz;

import java.util.Arrays;
import java.util.List;

public class StreamQuiz1 {

    public static void main(String[] args) {
        List<String> frutas = Arrays
                .asList("maçã", "uvas",
                        "abacaxi", "mamão", "banana");

        String resultado = frutas.stream()
                .filter(f -> f.length() > 4)
                .reduce("", (a, b) -> a + b);
        System.out.println(resultado);
    }

}
