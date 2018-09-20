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
        Function<BinaryOperator<String>, String> applyToFooBar = applyOperatorToStrings.apply("foo", "bar");

        // Now our code is clean
        assertEquals(__, applyToFooBar.apply(concat));
        assertEquals(__, applyToFooBar.apply(concatWithSpace));
    }

    @Koan
    public void pluggableUsingPreferences() {

        // first we define our preferences
        Map<String, String> preferences = new HashMap<>();
        preferences.put("foo", "bar");

        // now let's make some functions for acting on preference keys:
        BiFunction<String, Map<String, String>, String> concatKeyValue = (str, map) -> str + map.getOrDefault(str, "not found");
        BiFunction<String, Map<String, String>, String> showPrettyKeyValue = (str, map) -> str + ": " + map.getOrDefault(str, "not found");

        // now we can call these functions, but it's kind of ugly to have to pass the preferences every time.
        assertEquals(__, concatKeyValue.apply("foo", preferences));
        assertEquals(__, showPrettyKeyValue.apply("foo", preferences));
        assertEquals(__, concatKeyValue.apply("invalid", preferences));
        assertEquals(__, showPrettyKeyValue.apply("invalid", preferences));

        // Previously, we used currying to bury the preferences inside of the function.  That's fine,
        // but we have to repeat the pattern every time we define some use of a preference key.  Let's use
        // a partially applied function to make this a bit more generic.

        // Let's define a function that takes preferences, then returns a function that takes a parameter and returns
        // another function that takes a function that takes the parameter & preferences, then applied the function
        // and returns a result.  This is a bit to wrap your head around.
        Function<Map<String, String>, Function<String, Function<BiFunction<String, Map<String, String>, String>, String>>> applyOperatorToStringUsingPreferences =
            prefs -> (param -> (f -> f.apply(param, prefs) ));

        // Like the last example, we probably don't want to use this function directly, but we can:
        assertEquals(__, applyOperatorToStringUsingPreferences.apply(preferences).apply("foo").apply(concatKeyValue));
        assertEquals(__, applyOperatorToStringUsingPreferences.apply(preferences).apply("foo").apply(showPrettyKeyValue));

        // Instead of using our monster function directly, let's create a simpler function that already knows
        // about our preferences.  This is a smaller monster:
        Function<String, Function<BiFunction<String, Map<String, String>, String>, String>> applyOperatorToString =
                applyOperatorToStringUsingPreferences.apply(preferences);

        // Now we can knit our parameter and operator together in our code:
        assertEquals(__, applyOperatorToString.apply("foo").apply(concatKeyValue));
        assertEquals(__, applyOperatorToString.apply("foo").apply(showPrettyKeyValue));

        // This is still ugly (the smaller monster)
        // we can create different functions to act on different preference parameters without repeating code:
        Function<BiFunction<String, Map<String, String>, String>, String> applyOperatorToFoo = applyOperatorToString.apply("foo");
        assertEquals(__, applyOperatorToFoo.apply(concatKeyValue));
        assertEquals(__, applyOperatorToFoo.apply(showPrettyKeyValue));
    }
}
