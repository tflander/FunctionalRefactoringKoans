package introduction;

import com.sandwich.koan.Koan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * Core Java added the FunctionalInterface to several old interfaces.
 *
 * Among these are Runnable, Comparator, ActionListener, and FileFilter.
 *
 * We can use these interfaces the old way, or we can use them the Java8 way.
 */
public class AboutJavaCore {

    /**
     * We can create explicit named comparators by instantiating the interface with an override.
     * This is nice, because we have named comparators that explicitly call out the intention
     * of sorting default or sorting alphabetically.
     */
    @Koan
    public void comparatorOldWayExplicitNamed() {

        List<String> fruit = Arrays.asList("Pear", "Apple", "Bananna");

        Comparator<String> defaultOrder = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };

        Comparator<String> alphaOrder = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        fruit.sort(defaultOrder);
        assertEquals("Pear", fruit.get(0));

        fruit.sort(alphaOrder);
        assertEquals("Apple", fruit.get(0));
    }

    /**
     * We can inline the comparators to make our code smaller, but it's still bigger than we would like.
     * Worse yet, the intent of sorting default vs alphabetical is lost.  Future developers looking at
     * the code will have to think in order to deduce what the code does.
     */
    @Koan
    public void comparatorOldWayInline() {

        List<String> fruit = Arrays.asList("Pear", "Apple", "Bananna");

        fruit.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        assertEquals("Pear", fruit.get(0));

        fruit.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        assertEquals("Apple", fruit.get(0));
    }

    /**
     * We can use the old Comparator using lambdas assigned to a variable defined by a functional interface.
     * This makes our code more concise and easier to read, assuming lambdas are familiar.
     */
    @Koan
    public void comparatorNewWayExplicit() {

        List<String> fruit = Arrays.asList("Pear", "Apple", "Bananna");

        Comparator<String> defaultOrder = (s1, s2) -> 0;
        Comparator<String> alphaOrder = (s1, s2) -> s1.compareTo(s2);

        fruit.sort(defaultOrder);
        assertEquals("Pear", fruit.get(0));

        fruit.sort(alphaOrder);
        assertEquals("Apple", fruit.get(0));
    }

    /**
     * We can inline our lambas to make the code even more concise, but then we lose the intent of sorting
     * default vs sorting alphabetically.
     */
    @Koan
    public void comparatorNewWayImplicit() {

        List<String> fruit = Arrays.asList("Pear", "Apple", "Bananna");

        fruit.sort((s1, s2) -> 0);
        assertEquals("Pear", fruit.get(0));

        fruit.sort((s1, s2) -> s1.compareTo(s2));
        assertEquals("Apple", fruit.get(0));
    }
}
