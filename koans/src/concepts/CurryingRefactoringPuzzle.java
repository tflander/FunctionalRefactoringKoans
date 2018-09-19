package concepts;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.sandwich.util.Assert.assertEquals;

public class CurryingRefactoringPuzzle {

    Consumer<String> todo = Assert::fail;

    static class Transmogrifier {
        private final Map<String, String> preferences;

        public Transmogrifier(Map<String, String> preferences) {
            this.preferences = preferences;
        }

        public String doThing(String param) {
            return param + "=" + preferences.getOrDefault(param, "");
        }
    }

    static class MyTransmogrifier extends Transmogrifier {
        public MyTransmogrifier() {

            super(MyTransmogrifier.initPrefs());
        }

        private static Map<String, String> initPrefs() {
            Map<String, String> preferences = new HashMap<>();
            preferences.put("foo", "bar");
            return preferences;
        }
    }

    @Koan
    public void refactorWithCurrying() {
        todo.accept("Refactor the above classes to use currying instead of inheritance.\nObserve how many lines of code disappear.\nDon't forget to delete this line");
        MyTransmogrifier myTransmogrifier = new MyTransmogrifier();
        assertEquals("foo=bar", myTransmogrifier.doThing("foo"));
    }
}
