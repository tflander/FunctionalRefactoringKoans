package concepts;

import com.sandwich.koan.Koan;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutFunctionComposition {

    static Predicate<Integer> isLessThan10 = i -> i < 10;
    static Predicate<Integer> isGreaterThan2 = i -> i > 2;
    static Predicate<Integer> isOdd = i -> i % 2 != 0;

    int valueThatIsTrueForAllPredicates = 5;

    static Function<String, String> toLowerCase = str -> str.toLowerCase();
    static Function<String, String> dropFirstCharacter = str -> str.substring(1);

    @Koan
    public void predicateReview() {

        // TODO: fix assertion expectations
        assertEquals(false, isLessThan10.test(valueThatIsTrueForAllPredicates));
        assertEquals(false, isGreaterThan2.test(valueThatIsTrueForAllPredicates));
        assertEquals(false, isOdd.test(valueThatIsTrueForAllPredicates));
    }

    @Koan
    public void composedPredicate() {
        Predicate<Integer> isOddNumberBetween3and9 = isLessThan10.and(isGreaterThan2.and(isOdd));

        // TODO: fix assertion expectation
        assertEquals(false, isOddNumberBetween3and9.test(valueThatIsTrueForAllPredicates));

    }

    @Koan
    public void composedPredicateWithNegation() {
        int evenValueThatPassesOtherTwoPredicates = 4;

        Predicate<Integer> isEvenNumberBetween4and8 = isGreaterThan2.and(isLessThan10.and(isOdd.negate()));

        // TODO: fix assertion expectation
        assertEquals(false, isEvenNumberBetween4and8.test(evenValueThatPassesOtherTwoPredicates));
    }

    @Koan
    public void functionComposition() {
        String testString = "JAVA";

        assertEquals(__, dropFirstCharacter.apply(testString));
        assertEquals(__, toLowerCase.apply(testString));
        Function<String, String> dropFirstCharacterAndConvertToLowerCase = dropFirstCharacter.andThen(toLowerCase);

        assertEquals(__, dropFirstCharacterAndConvertToLowerCase.apply(testString));
    }


}
