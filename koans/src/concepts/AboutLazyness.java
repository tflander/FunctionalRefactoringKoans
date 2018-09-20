package concepts;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.sandwich.util.Assert.assertFalse;
import static com.sandwich.util.Assert.assertTrue;

public class AboutLazyness {

    Consumer<String> todo = Assert::fail;

    private boolean slowDataCalled;
    private boolean slowDataUsed;

    private List<Integer> numbers = Arrays.asList(2, 4, 6, 8);

    @Koan
    public void eager() {
        slowDataCalled = false;
        slowDataUsed = false;

        processNumbers(numbers, slowDataCall());
        todo.accept("Contemplate why we made the slow data call, but didn't use the data.\nDelete this line to continue.");
        assertTrue(slowDataCalled);
        assertFalse(slowDataUsed);
    }

    @Koan
    public void functionalLazyness() {
        slowDataCalled = false;
        slowDataUsed = false;

        processNumbersLazy(numbers, () -> slowDataCall());
        todo.accept("Understand why we didn't have to make the call to get the slow data.\nDelete this line to continue.");
        assertFalse(slowDataCalled);
        assertFalse(slowDataUsed);
    }

    private String slowDataCall() {
        this.slowDataCalled = true;
        return "foo";
    }

    private void processNumbers(List<Integer> numbers, String dataFromslowDataCall) {
        if (numbers.stream().mapToInt(i -> i).sum() > 30) {
            doSomethingWith(dataFromslowDataCall);
        }
    }

    private void processNumbersLazy(List<Integer> numbers, Supplier<String> slowDataSupplier) {
        if (numbers.stream().mapToInt(i -> i).sum() > 30) {
            doSomethingWith(slowDataSupplier.get());
        }
    }

    private void doSomethingWith(String dataFromslowDataCall) {
        slowDataUsed = true;
    }

}
