package basics;

import com.sandwich.koan.Koan;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * Core Java added the FunctionalInterface to several old interfaces.  We already took a look at Runnable.
 *
 * Among these are Comparator, ActionListener, and FileFilter.
 *
 * Since these interfaces follow the SAM rules, they are suitable for lambdas.
 */
public class AboutJavaCore {

    List<String> fruit = Arrays.asList("Pear", "Apple", "Bananna");

    @Koan
    public void comparatorOldWay() {

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
        assertEquals(__, fruit.get(0));

        fruit.sort(alphaOrder);
        assertEquals(__, fruit.get(0));
    }

    @Koan
    public void comparatorNewWay() {

        Comparator<String> defaultOrder = (s1, s2) -> 0;
        Comparator<String> alphaOrder = (s1, s2) -> s1.compareTo(s2);

        fruit.sort(defaultOrder);
        assertEquals(__, fruit.get(0));

        fruit.sort(alphaOrder);
        assertEquals(__, fruit.get(0));
    }
}
