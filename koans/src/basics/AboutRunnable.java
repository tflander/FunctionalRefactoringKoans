package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Runnable is overlooked as a functional interface.  Runnables are functions that take no arguments and return no
 * arguments.
 */
public class AboutRunnable {

    Consumer<String> todo = Assert::fail;

    /**
     * An impractical example of Runnable
     */
    @Koan
    public void runnable() {
        Runnable doNothing = () -> {};
        doNothing.run();
    }

    /**
     * This example touches on lazyness and currying.  We will explore these concepts later.
     */
    @Koan
    public void morePracticalRunnable() {

        Consumer<String> printString = str -> System.out.println(str);

        List<Runnable> lazyCommands = new ArrayList<>();
        lazyCommands.add(() -> printString.accept("foo"));
        lazyCommands.add(() -> printString.accept("bar"));
        lazyCommands.add(() -> printString.accept("baz"));

        lazyCommands.forEach(Runnable::run);
    }
}
