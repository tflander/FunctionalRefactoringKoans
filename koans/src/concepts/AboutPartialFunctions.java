package concepts;

import com.sandwich.koan.Koan;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutPartialFunctions {

    @Koan
    public void pluggableStringConcatination() {

        // Let's define some operators that we might want to apply to two strings
        BinaryOperator<String> concat = (s1, s2) -> s1 + s2;
        BinaryOperator<String> concatWithSpace = (s1, s2) -> s1 + " " + s2;

        // Now we have nice functions that allow us to concat as we wish:
        assertEquals(__, concat.apply("foo", "bar"));
        assertEquals(__, concatWithSpace.apply("foo", "bar"));

        // Suppose we discover that we need a new module that needs to be able to plug in the concat operator,
        // rather than the two strings.  We've designed things backwards from that module's perspective.
        // We can start over with a new functional interface, or we can leverage existing code by
        // creating a partially applied function.

        // Now we define a function that takes two strings, and returns a function that takes
        // a binary operator to act on those strings, then returns the applied result.
        // This is a bit of a monster.
        BiFunction<String, String, Function<BinaryOperator<String>, String>> applyOperatorToStrings =
                (s1, s2) -> (f -> f.apply(s1, s2));

        // This function is ugly, so we probably don't want to use it directly.  If we did, it
        // looks like this:
        assertEquals(__, applyOperatorToStrings.apply("foo", "bar").apply(concat));
        assertEquals(__, applyOperatorToStrings.apply("foo", "bar").apply(concatWithSpace));

        // Let's make the function that conforms to the specification of our new module, where the
        // two strings are known, but we need to be able to easily plug in the concatination function:
        Function<BinaryOperator<String>, String> applyToFooBar = f -> f.apply("foo", "bar");

        // Now our code is clean
        assertEquals(__, applyToFooBar.apply(concat));
        assertEquals(__, applyToFooBar.apply(concatWithSpace));
    }

    @Koan
    public void pluggableUsingPreferences() {
        Map<String, String> preferences = new HashMap<>();
        preferences.put("foo", "bar");

        BiFunction<String, Map<String, String>, String> concatKeyValue = (str, map) -> str + map.getOrDefault(str, "not found");
        BiFunction<String, Map<String, String>, String> showPrettyKeyValue = (str, map) -> str + ": " + map.getOrDefault(str, "not found");

        assertEquals(__, concatKeyValue.apply("foo", preferences));
        assertEquals(__, showPrettyKeyValue.apply("foo", preferences));

        Function<Map<String, String>, Function<String, Function<BiFunction<String, Map<String, String>, String>, String>>> applyOperatorToStringUsingPreferences =
            prefs -> (param -> (f -> f.apply(param, prefs) ));

        assertEquals(__, applyOperatorToStringUsingPreferences.apply(preferences).apply("foo").apply(concatKeyValue));
        assertEquals(__, applyOperatorToStringUsingPreferences.apply(preferences).apply("foo").apply(showPrettyKeyValue));

        Function<String, Function<BiFunction<String, Map<String, String>, String>, String>> applyOperatorToString = applyOperatorToStringUsingPreferences.apply(preferences);
        assertEquals("foobar", applyOperatorToString.apply("foo").apply(concatKeyValue));
        assertEquals("foo: bar", applyOperatorToString.apply("foo").apply(showPrettyKeyValue));

        Function<BiFunction<String, Map<String, String>, String>, String> applyOperatorToFoo = applyOperatorToString.apply("foo");
        assertEquals("foobar", applyOperatorToFoo.apply(concatKeyValue));
        assertEquals("foo: bar", applyOperatorToFoo.apply(showPrettyKeyValue));
    }
}
