package basics;

import com.sandwich.koan.Koan;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * Operators are functions that take one or more object of a type, then return an object of the same type.
 * You can use the Function<> interface, but the Operator interface is more specific.
 */
public class AboutOperators {

    /**
     * Unary operators take an object of type T and return an object of the same type.
     */
    @Koan
    public void stringDoublerUnaryOperator() {
        UnaryOperator<String> doubler = str -> str + str;

        assertEquals(__, doubler.apply("foo"));
    }

    /**
     * BinaryOperators take two objects of type T and return an object of the same type.
     */
    @Koan
    public void stringConcatBinaryOperator() {
        BinaryOperator<String> concat = (s1, s2) -> s1 + s2;

        assertEquals(__, concat.apply("foo", "bar"));
    }

}
