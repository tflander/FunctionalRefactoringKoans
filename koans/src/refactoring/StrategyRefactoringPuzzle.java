package refactoring;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.sandwich.util.Assert.assertEquals;
import static refactoring.StrategyRefactoringPuzzle.Voter.Party.DEMOCRAT;
import static refactoring.StrategyRefactoringPuzzle.Voter.Party.REPUBLICAN;
import static refactoring.StrategyRefactoringPuzzle.Voter.Sex.FEMALE;
import static refactoring.StrategyRefactoringPuzzle.Voter.Sex.MALE;

public class StrategyRefactoringPuzzle {

    Consumer<String> todo = Assert::fail;

    // TODO: You will need four Voter Predicates for this refactor (isFemale, isMale, isRepublican, and isDemocrat)
    // TODO: all of the Koans pass.  Keep them passing with minimal and sufficient code.
    // TODO: scroll down and follow the rest of the TODO statements

    @Koan
    public void voterCount() {
        todo.accept("Delete this line and refactor the code by following the TODO comments");
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

    // TODO: keep this class as-is
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

    // TODO: keep this list as-is
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

    // TODO: delete this class
    static class DemocratCountVoterStrategy implements CountVoterStrategy {
        @Override
        public boolean shouldCount(Voter voter) {
            return voter.party == DEMOCRAT;
        }
    }

    // TODO: delete this class
    static class FemaleCountVoterStrategy implements CountVoterStrategy {
        @Override
        public boolean shouldCount(Voter voter) {
            return voter.sex == FEMALE;
        }
    }


    // TODO: delete this class
    static class CountVoterCompositeStrategy implements CountVoterStrategy {

        private final List<CountVoterStrategy> composites;

        public CountVoterCompositeStrategy(List<CountVoterStrategy> composites) {

            this.composites = composites;
        }

        @Override
        public boolean shouldCount(Voter voter) {
            for (CountVoterStrategy strategy : composites) {
                if (!strategy.shouldCount(voter)) {
                    return false;
                }
            }
            return true;
        }
    }

    // TODO: delete this class
    static class FemaleDemocratCountVoterStrategy extends CountVoterCompositeStrategy {

        private static List<CountVoterStrategy> composites = new ArrayList<>();

        static {
            composites.add(new FemaleCountVoterStrategy());
            composites.add(new DemocratCountVoterStrategy());
        }

        public FemaleDemocratCountVoterStrategy() {
            super(composites);
        }
    }

    // TODO: delete this class
    static class MaleCountVoterStrategy implements CountVoterStrategy {
        @Override
        public boolean shouldCount(Voter voter) {
            return voter.sex == MALE;
        }
    }

    // TODO: delete this class
    static class FemaleRepublicanCountVoterStrategy extends CountVoterCompositeStrategy {

        private static List<CountVoterStrategy> composites = new ArrayList<>();

        static {
            composites.add(new FemaleCountVoterStrategy());
            composites.add(new RepublicanCountVoterStrategy());
        }

        public FemaleRepublicanCountVoterStrategy() {
            super(composites);
        }
    }


    // TODO: delete this class
    static class RepublicanCountVoterStrategy implements CountVoterStrategy {

        @Override
        public boolean shouldCount(Voter voter) {
            return voter.party == REPUBLICAN;
        }
    }

    // TODO: delete this class
    static class MaleDemocratCountVoterStrategy extends CountVoterCompositeStrategy {

        private static List<CountVoterStrategy> composites = new ArrayList<>();

        static {
            composites.add(new MaleCountVoterStrategy());
            composites.add(new DemocratCountVoterStrategy());
        }

        public MaleDemocratCountVoterStrategy() {
            super(composites);
        }
    }

    // TODO: delete this class
    static class MaleRepublicanCountVoterStrategy extends CountVoterCompositeStrategy {

        private static List<CountVoterStrategy> composites = new ArrayList<>();

        static {
            composites.add(new MaleCountVoterStrategy());
            composites.add(new RepublicanCountVoterStrategy());
        }

        public MaleRepublicanCountVoterStrategy() {
            super(composites);
        }
    }

    // TODO: keep this function as-is
    static int count(List<Voter> voters) {
        return voters.size();
    }

    // TODO: delete this interface
    static interface CountVoterStrategy {
        boolean shouldCount(Voter voter);
    }

    // TODO: rename this function to countUsingFilter and return a function that takes a list of voters and
    // TODO: returns the count.  You need a Voter Predicate as an input parameter countUsingFilter()
    static int countUsingStrategy(List<Voter> voters, CountVoterStrategy countVoterStrategy) {
        return (int) voters.stream()
                .filter(countVoterStrategy::shouldCount)
                .count();
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countMales(List<Voter> voters) {
        return countUsingStrategy(voters, new MaleCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countFemales(List<Voter> voters) {
        return countUsingStrategy(voters, new FemaleCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countDemocrats(List<Voter> voters) {
        return countUsingStrategy(voters, new DemocratCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countRepublicans(List<Voter> voters) {
        return countUsingStrategy(voters, new RepublicanCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countFemaleDemocrats(List<Voter> voters) {
        return countUsingStrategy(voters, new FemaleDemocratCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countMaleDemocrats(List<Voter> voters) {
        return countUsingStrategy(voters, new MaleDemocratCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countFemaleRepublicans(List<Voter> voters) {
        return countUsingStrategy(voters, new FemaleRepublicanCountVoterStrategy());
    }

    // TODO: replace the function body without changing the behavior or function signature
    static int countMaleRepublicans(List<Voter> voters) {
        return countUsingStrategy(voters, new MaleRepublicanCountVoterStrategy());
    }

}
