package ada.tech;

import io.reactivex.rxjava3.core.Observable;
import static org.junit.jupiter.api.Assertions.*;

import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ReactiveTest {

    @Test
    public void map() {
        AtomicReference<List<Integer>> result = new AtomicReference<>(new ArrayList<>());
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 11);
        observable
            .filter(k -> k % 2 == 1)
            .map(a -> a * 2)
            .subscribe( n -> result.get().add(n) );

        assertEquals(List.of(2, 6, 10, 14, 22), result.get());
    }

    @Test
    public void apenasParesSoma() throws InterruptedException {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        TestObserver<Integer> testObserver = new TestObserver<>();

        observable
            .filter(numero -> numero % 2 == 0)
            .reduce( (a, b) -> a + b)
            .subscribe(testObserver);

        testObserver.assertValue(30);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void startWithAndMerge() {
        Observable<Integer> source1 = Observable.just(1, 2);
        Observable<Integer> source2 = Observable.just(3, 4);
        TestObserver<Integer> testObserver = new TestObserver<>();

        source1
            .startWithItem(0)
            .mergeWith(source2)
            .subscribe(testObserver);

        testObserver.assertValueSequence(Arrays.asList(0, 1, 2, 3, 4));
    }

}