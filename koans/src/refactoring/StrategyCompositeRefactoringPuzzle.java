package refactoring;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.sandwich.util.Assert.assertEquals;
import static refactoring.StrategyCompositeRefactoringPuzzle.Voter.Party.DEMOCRAT;
import static refactoring.StrategyCompositeRefactoringPuzzle.Voter.Party.REPUBLICAN;
import static refactoring.StrategyCompositeRefactoringPuzzle.Voter.Sex.FEMALE;
import static refactoring.StrategyCompositeRefactoringPuzzle.Voter.Sex.MALE;

public class StrategyCompositeRefactoringPuzzle {

    Consumer<String> todo = Assert::fail;

    static Predicate<Voter> isFemale = voter -> voter.sex == FEMALE;
    static Predicate<Voter> isMale = voter -> voter.sex == MALE;
    static Predicate<Voter> isRepublican = voter -> voter.party == REPUBLICAN;
    static Predicate<Voter> isDemocrat = voter -> voter.party == DEMOCRAT;

    @Koan
    public void voterCount() {
        assertEquals(15, count(voters));
    }

    @Koan
    public void males() {
        assertEquals(7, countMales(voters));
    }

    @Koan
    public void females() {
        assertEquals(8, countFemales(voters));
    }

    @Koan
    public void democrats() {
        assertEquals(6, countDemocrats(voters));
    }

    @Koan
    public void republicans() {
        assertEquals(9, countRepublicans(voters));
    }

    @Koan
    public void femaleDemocrats() {
        assertEquals(2, countFemaleDemocrats(voters));
    }

    @Koan
    public void maleDemocrats() {
        assertEquals(4, countMaleDemocrats(voters));
    }

    @Koan
    public void femaleRepublicans() {
        assertEquals(6, countFemaleRepublicans(voters));
    }

    @Koan
    public void maleRepublicans() {
        assertEquals(3, countMaleRepublicans(voters));
    }

    static class Voter {
        public enum Sex {MALE, FEMALE}

        public enum Party {DEMOCRAT, REPUBLICAN}

        public final Sex sex;
        public final Party party;

        public Voter(Sex sex, Party party) {
            this.sex = sex;
            this.party = party;
        }
    }

    private final List<Voter> voters = Arrays.asList(
            new Voter(MALE, REPUBLICAN),
            new Voter(MALE, DEMOCRAT),
            new Voter(MALE, REPUBLICAN),
            new Voter(MALE, DEMOCRAT),
            new Voter(MALE, DEMOCRAT),
            new Voter(MALE, DEMOCRAT),
            new Voter(MALE, REPUBLICAN),
            new Voter(FEMALE, DEMOCRAT),
            new Voter(FEMALE, REPUBLICAN),
            new Voter(FEMALE, DEMOCRAT),
            new Voter(FEMALE, REPUBLICAN),
            new Voter(FEMALE, REPUBLICAN),
            new Voter(FEMALE, REPUBLICAN),
            new Voter(FEMALE, REPUBLICAN),
            new Voter(FEMALE, REPUBLICAN)
    );

    static int count(List<Voter> voters) {
        return voters.size();
    }

    static int countUsingFilter(List<Voter> voters, Predicate<Voter> filter) {
        return (int) voters.stream()
                .filter(filter)
                .count();
    }

    static int countMales(List<Voter> voters) {
        return countUsingFilter(voters, isMale);
    }

    static int countFemales(List<Voter> voters) {
        return countUsingFilter(voters, isFemale);
    }

    static int countDemocrats(List<Voter> voters) {
        return countUsingFilter(voters, isDemocrat);
    }

    static int countRepublicans(List<Voter> voters) {
        return countUsingFilter(voters, isRepublican);
    }

    static int countFemaleDemocrats(List<Voter> voters) {
        return countUsingFilter(voters, isFemale.and(isDemocrat));
    }

    static int countMaleDemocrats(List<Voter> voters) {
        return countUsingFilter(voters, isMale.and(isDemocrat));
    }

    static int countFemaleRepublicans(List<Voter> voters) {
        return countUsingFilter(voters, isFemale.and(isRepublican));
    }

    static int countMaleRepublicans(List<Voter> voters) {
        return countUsingFilter(voters, isMale.and(isRepublican));
    }

}
