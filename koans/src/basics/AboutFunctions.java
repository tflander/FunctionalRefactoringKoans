package basics;

import com.sandwich.koan.Koan;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


public class AboutFunctions {

    /**
     * The Function<T, R> interface is a template that allows you to define functions
     * that takes an object of type T and returns a result of type R
     *
     * This is good (among other things) for creating mapping and translation functions.
     */
    @Koan
    public void conversionFunction() {
        Function<Integer, String> integerAsString = i -> __;
        assertEquals("123", integerAsString.apply(123));
    }

    /**
     * You can also use Functions to create an aggregator, since the input type T could be a
     * collection.
     */
    @Koan
    public void sumIntegers() {

        // TODO: finish this code
        Function<List<Integer>, Integer> sumIntegers = list -> 0;  // TODO: For now stub zero

        List<Integer> numbersToSum = Arrays.asList(1, 2, 3);
        assertEquals(6, sumIntegers.apply(numbersToSum));

        // hint
        assertEquals(6, numbersToSum.stream().mapToInt(i->i).sum());
    }
}
