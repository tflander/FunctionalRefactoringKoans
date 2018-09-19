package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.function.Consumer;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * SAM stands for Single Abstract Method.  Any SAM can by used as a functional interface.
 *
 * The @FunctionalInterface annotation enforces SAM rules:
 *   -
 */
public class AboutSam {

    Consumer<String> todo = Assert::fail;

    @FunctionalInterface
    static interface SimpleSam {
        String foo(String input);
    }

    @FunctionalInterface
    static interface ComplexSam {
        String foo(String input);

        static String addPrefixAndSuffix(String input) {
            return "prefix " + input + " suffix";
        }
    }

    // @FunctionalInterface  // Compile Error: Multiple non-overriding abstract methods found in interface
    static interface NotSam {
        String foo(String input);
        String bar(String input);
    }

    // @FunctionalInterface  // Compile Error: No target method found
    static interface AlsoNotSam {
        default String foo(String input) {return "whoops";};
    }


    @Koan
    public void stringIdentityViaSimpleSam() {
        SimpleSam stringIdentity = str -> str;
        assertEquals(__, stringIdentity.foo("bar"));
    }

    @Koan
    public void stringWrapViaComplexSam() {
        ComplexSam stringIdentityWrapper = str -> ComplexSam.addPrefixAndSuffix(str);
        assertEquals(__, stringIdentityWrapper.foo("bar"));

        ComplexSam doOurOwnThing = str -> "[" + str + "]";
        assertEquals(__, doOurOwnThing.foo("bar"));
    }

    @Koan
    public void notSam() {
        todo.accept("Understand why this code does not compile.\nDelete this line to continue.");
        // Compile Error: Multiple non-overriding abstract methods found in interface
        // NotSam whoops = str -> str;
    }

    @Koan
    public void alsoNotSam() {
        todo.accept("Understand why this code does not compile.\nDelete this line to continue.");
        // Compile Error: No target method found
        // AlsoNotSam whoops = str -> str;
    }

}
