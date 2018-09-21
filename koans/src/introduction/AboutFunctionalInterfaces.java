package introduction;

import com.sandwich.koan.Koan;

import java.util.function.*;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * After doing functional programming in Scala for a couple years, I was disappointed to learn about the
 * Java8 functional interfaces.  They seemed complex and restrictive.  Scala did not have these interfaces.
 *
 * Years later, I realized that the restrictive interfaces encourage the design of simple functionality that
 * can be easily composed into complex functionality.
 *
 * Here is the Java8 reference for functional interfaces:
 *  https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 *
 * Here is a good breakdown by type of function interface:
 *  https://www.baeldung.com/java-8-functional-interfaces
 *
 */
public class AboutFunctionalInterfaces {

    private String lastConsumedString;

    /**
     * Functions take a value of type T and return a value of type R.
     * Call the apply() method on the function to pass the input and get the output.
     */
    @Koan
    public void function() {
        Function<Integer, String> integerToString = i -> i.toString();
        assertEquals(integerToString.apply(123), __);
    }

    /**
     * Suppliers take no arguments, but return a value.
     * Call the get() method on the supplier function to get the value.
     */
    @Koan
    public void supplier() {
        Supplier<String> supplyGreeting = () -> "hello";
        assertEquals(__, supplyGreeting.get());
    }

    /**
     * Consumers are the opposite of suppliers.  They take input and return void.
     * call the accept() method to consume input.
     */
    @Koan
    public void consumer() {
        Consumer<String> consumeString = str -> this.lastConsumedString = str;
        consumeString.accept("howdy");
        assertEquals(__, lastConsumedString);
    }

    /**
     * Predicates take input and return boolean.
     * Call the test() method to take input and return true or false.
     */
    @Koan
    public void predicate() {
        Predicate<Integer> isOdd = i -> i % 2 != 0;

        // TODO: change the expectation to get this assertion to pass
        assertEquals(true, isOdd.test(4));
    }

    /**
     * UnaryOperators take input and return output of the same type.
     * Call the apply() function to input data and get the operator result
     */
    @Koan
    public void unaryOperator() {
        UnaryOperator<String> doubleString = str -> str + str;
        assertEquals(__, doubleString.apply("foo"));
    }

    /**
     * BinaryOperators take two objects of the same type and return a third object of the same type.
     * Call the apply() function to input the two objects and get the result.
     */
    @Koan
    public void BinaryOperator() {
        BinaryOperator<String> concatStrings = (s1, s2) -> s1 + s2;
        assertEquals(__, concatStrings.apply("foo", "bar"));
    }

    /**
     * BiFunctions are like functions, except they take two input objects instead of one.
     * The input objects may be the same type or different types.
     * Call the apply() function to take the two inputs and return the result.
     *
     * There are Bi versions of Function, Consumer, and Predicate that also take two inputs.
     */
    @Koan
    public void BiFunction() {
        BiFunction<Character, Integer, String> repeatChar = (token, count) -> {
          StringBuilder sb = new StringBuilder();
          for(int i = 0; i < count; ++i) {
              sb.append(token);
          }
          return sb.toString();
        };

        assertEquals(__, repeatChar.apply('*', 4));
    }

    /**
     * Note the Javadocs for functional interfaces contains a bunch of primitive types.
     * https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
     *
     * These are provided to allow the use of primitives and to avoid the overhead of auto-boxing.
     * The functional interfaces use Java generics, which do not support primitives without auto-boxing.
     */
    @Koan
    public void primitives() {

        UnaryOperator<Integer> incrementViaAutoBoxing = i -> ++i;
        IntUnaryOperator incrementPrimitive = i -> ++i;
        int replaceMe = -1;

        assertEquals(replaceMe, incrementViaAutoBoxing.apply(1));
        assertEquals(replaceMe, incrementPrimitive.applyAsInt(1));
    }
}
