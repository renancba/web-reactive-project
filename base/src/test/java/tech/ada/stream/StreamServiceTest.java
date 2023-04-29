package tech.ada.stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tech.ada.streams.StreamsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamServiceTest {

    StreamsService service = new StreamsService();

    @Deprecated
    public static void main(String[] args) {
        List<Integer> entry = List.of(1,2,3,4,5); // entrada
        StreamsService service = new StreamsService(); // service
        Optional<Integer> firstOdd = service.firstOdd(entry); // resultado
        System.out.println( firstOdd.get() );
    }

    // criar Usuario nome, idade, saldo
    // campos obrigatorios
    // dado valido data-idade
    // dado valido saldo
    //[18-65]

    @Test
    public void firstOdd() {
        List<Integer> entry = List.of(1,2,3,4,5);
        Optional<Integer> firstOdd = service.firstOdd(entry);
        assertNotNull(firstOdd);
        assertFalse(firstOdd.isEmpty());
        assertEquals(1, firstOdd.get());
    }

    @Test
    public void odds() {
        List<Integer> entry = List.of(1,2,3,4,5);
        List<Integer> odds = service.getOdd(entry);
        assertEquals(Arrays.asList(1,3,5), odds);
    }

    @Test
    public void hasOdd() {
        List<Integer> entry = List.of(2,4,6);
        boolean arentOdd = service.hasOdd(entry);
        assertFalse( arentOdd );
    }

}