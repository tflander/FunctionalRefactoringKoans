package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.sandwich.util.Assert.assertEquals;

/**
 * Predicates always return boolean, regardless of the input paramter type
 */
public class AboutPredicates {

    Consumer<String> todo = Assert::fail;

    @Koan
    public void lessThan10() {

        Predicate<Integer> lessThanTen = i -> i < 10;

        // Fix the assertions
        assertEquals(false, lessThanTen.test(3));
        assertEquals(true, lessThanTen.test(30));
    }

    /**
     * We can use named predicates for syntactic sugar in our code. We can filter a Java 8 stream using a
     * lamda, but it can be more expressive to explicitly define a predicate, especially if this is a predicate
     * we might want to inject or re-use.
     */
    @Koan
    public void streamFiltering() {
        Predicate<Integer> lessThanTen = i -> i < 10;
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 12);
        List<Integer> expectedNumbersLessThanTen = Arrays.asList(2, 4, 6, 8);

        todo.accept("Here we use our predicate, rather than an anonymous lamba.\n Delete this line to continue.");
        List<Integer> actualNumbersLessThanTen = numbers.stream()
                .filter(lessThanTen)
                .collect(Collectors.toList());

        assertEquals(expectedNumbersLessThanTen, actualNumbersLessThanTen);
    }
}
