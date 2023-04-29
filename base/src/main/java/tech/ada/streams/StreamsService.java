package tech.ada.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsService {
    public Optional<Integer> firstOdd(List<Integer> entry) {
        if (entry == null || entry.isEmpty() ) return Optional.ofNullable(-1);
        return entry.stream()
            .filter(e -> e % 2 == 1)
            .findFirst();
    }

    public List<Integer> getOdd(List<Integer> entry) {
        if (entry == null || entry.isEmpty() ) return List.of();
        return entry.stream()
                .filter(e -> e % 2 == 1)
                .collect(Collectors.toList());
    }

    public boolean hasOdd(List<Integer> entry) {
        if (entry == null || entry.isEmpty() ) return false;
        return entry.stream()
                .anyMatch(n -> n % 2 != 0);
    }
}
