package basics;

import com.sandwich.koan.Koan;

import java.util.function.IntFunction;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * The javadoc for java.util.function describes many functional interfaces for using primitives.
 * We will just explore one.
 */
public class AboutPrimitives {

    @Koan
    public void primitiveIntFunction() {

        // This does not compile
        // Function<int, String> whoops = i -> "Number: " + i;  // Error: Type argument can't be a primitive type

        // Do this instead
        IntFunction<String> numberMessage = i -> "Number: " + i;
        assertEquals(__, numberMessage.apply(10));
    }
}
