package concepts;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.sandwich.util.Assert.assertEquals;

public class CurryingRefactoringPuzzle {

    Consumer<String> todo = Assert::fail;

    BiFunction<String, Map<String, String>, String> getFromPreferences = (key, preferences) ->
            key + "=" + preferences.getOrDefault(key, "");

    @Koan
    public void refactorWithCurrying() {
        Function<String, String> getKey = constructGetKey();
        assertEquals("foo=bar", getKey.apply("foo"));
    }

    private Function<String, String> constructGetKey() {
        Map<String, String> preferences = new HashMap<>();
        preferences.put("foo", "bar");
        return key -> getFromPreferences.apply(key, preferences);
    }
}
