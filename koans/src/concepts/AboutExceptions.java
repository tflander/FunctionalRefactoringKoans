package concepts;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.fail;

public class AboutExceptions {

    Consumer<String> todo = Assert::fail;

    final static Map<String, String> preferences = new HashMap<>();
    static {
        preferences.put("foo", "bar");
    }

    String getPreference(String key) throws Exception {
        if(!preferences.containsKey(key)) {
            throw new Exception("checked exception for invalid key" + key);
        }
        return preferences.get(key);
    }

    String getPreferenceUncheckedWrapper(String key) {
        try {
            return getPreference(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String getPreferenceUnchecked(String key) {
        if(!preferences.containsKey(key)) {
            throw new IllegalArgumentException("checked exception for invalid key" + key);
        }
        return preferences.get(key);
    }

    @FunctionalInterface
    static interface StringOperatorWithCheckedException {
        String apply(String input) throws Exception;
    }

    @Koan
    public void exceptions() {
        // Does not compile:  Unhandled Exception
//         UnaryOperator<String> preferenceLookup = this::getPreference;
    }

    /**
     * We could define our own functional interface to allow checked exceptions, but if there is no elegant way
     * to handle the exception, we should probably take a different approch.
     */
    @Koan
    public void worstApproach() {
        StringOperatorWithCheckedException preferenceLookup = this::getPreference;
        try {
            assertEquals("bar", preferenceLookup.apply("foo"));
        } catch (Exception e) {
            fail("this should not have happened");
        }
    }

    /**
     * We can wrap the function that throws the checked exception with a delegate that re-throws the exception
     * as unchecked.  Hopefully modules are protected with automated tests that make this safe.
     */
    @Koan
    public void betterApproach() {
         UnaryOperator<String> preferenceLookup = this::getPreferenceUncheckedWrapper;
        assertEquals("bar", preferenceLookup.apply("foo"));
    }

    /**
     * Java has unchecked exceptions for illegal arguments and illegal states.  Just use those.
     * You don't always have the option to replace checked exceptions with unchecked exceptions in code.
     * For those cases, use the wrapper approach.
     */
    @Koan
    public void bestApproach() {
        UnaryOperator<String> preferenceLookup = this::getPreferenceUnchecked;
        assertEquals("bar", preferenceLookup.apply("foo"));
    }
}
