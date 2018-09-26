package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.assertTrue;

/**
 * The javadoc for java.util.function describes many functional interfaces for using primitives.
 * We will just explore one.
 */
public class AboutPrimitives {

    Consumer<String> todo = Assert::fail;

    @Koan
    public void primitiveIntFunction() {

        // This does not compile
        // Function<int, String> whoops = i -> "Number: " + i;  // Error: Type argument can't be a primitive type

        // Do this instead
        IntFunction<String> numberMessage = i -> "Number: " + i;
        assertEquals("Number: 10", numberMessage.apply(10));
    }

    @Koan
    public void autoboxingOverhead() {

        IntFunction<String> primitiveIntMessage = i -> "Number: " + i;
        Function<Integer, String> objectIntegersMessage = i -> "Number: " + i;

        // measure how long it takes to run each function 10 million times.  Repeat to get 5 measurements for each function.
        System.out.println("please wait");
        List<Integer> primitiveTimes = new ArrayList<>();
        List<Integer> objectTimes = new ArrayList<>();

        // TODO: When you are done with this koan, comment out the rest of these lines because they are slow
//        gatherTimings(primitiveIntMessage, objectIntegersMessage, primitiveTimes, objectTimes);
//        displayTimings(primitiveTimes, objectTimes);
//
//        int maxTimeUsingPrimitives = primitiveTimes.stream().mapToInt(i -> i).max().getAsInt();
//        int minTimeUsingIntegers = objectTimes.stream().mapToInt(i -> i).min().getAsInt();
//
//        // Verify the max time using primitives is less than the min time using integers
//        assertTrue(maxTimeUsingPrimitives < minTimeUsingIntegers);

    }

    private void gatherTimings(IntFunction<String> primitiveIntMessage, Function<Integer, String> objectIntegersMessage, List<Integer> primitiveTimes, List<Integer> objectTimes) {
        int numberOfRuns = 10000000;
        for (int j = 0; j < 5; ++j) {
            Date start = new Date();
            for (int i = 0; i < numberOfRuns; ++i) {
                primitiveIntMessage.apply(i);
            }
            primitiveTimes.add((int) (new Date().getTime() - start.getTime()));

            start = new Date();
            for (int i = 0; i < numberOfRuns; ++i) {
                objectIntegersMessage.apply(i);
            }
            objectTimes.add((int) (new Date().getTime() - start.getTime()));
        }
    }

    private void displayTimings(List<Integer> primitiveTimes, List<Integer> objectTimes) {
        System.out.println("primitive times:");
        primitiveTimes.forEach(time -> System.out.println(time));
        System.out.println("object times:");
        objectTimes.forEach(time -> System.out.println(time));
    }

}
