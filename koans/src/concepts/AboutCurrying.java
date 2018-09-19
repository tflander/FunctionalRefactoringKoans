package concepts;

import com.sandwich.koan.Koan;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * Currying seems scary until you get used to it.  It's named after Haskell Curry, so right off the bat
 * it sounds abstract and mysterious.
 *
 * I like to think of currying as a function constructor.  Just like we can write code to construct objects,
 * we can write code to construct functions.
 *
 * Just like an object can change behavior based on construction parameters, a function can do the same.
 *
 * When you store an attribute from a constructor argument, you bury that attribute in the object, so you
 * don't have to pass it to the methods.  We can do the same thing when we construct a function.
 *
 */
public class AboutCurrying {

    @Koan
    public void basicExample() {
        BinaryOperator<String> joinStrings = (a,b) -> a + ' ' + b;
        assertEquals(__, joinStrings.apply("hello","world"));

        UnaryOperator<String> sayHello = subject -> joinStrings.apply("hello", subject);
        assertEquals(__, sayHello.apply("my friend"));
    }

    @Koan
    public void dataHidingExample() {
        Map<String, String> myPreferences = new HashMap<>();
        myPreferences.put("foo", "bar");

        BiFunction<Map<String, String>, String, String> uncurriedFunction = (preferences, param) ->
                param + "=" + preferences.getOrDefault(param, "");

        assertEquals("__", uncurriedFunction.apply(myPreferences, "foo"));

        Function<String, String> curriedFunction = createFunctionWithPreferences(myPreferences);
        assertEquals("__", curriedFunction.apply("foo"));
    }

    private Function<String, String> createFunctionWithPreferences(Map<String, String> preferences) {
        return param -> param + "=" + preferences.getOrDefault(param, "");
    }

}
