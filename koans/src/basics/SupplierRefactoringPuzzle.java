package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class SupplierRefactoringPuzzle {

    static abstract class Animal {
        public abstract String speak();
    }

    static class Cow extends Animal {

        @Override
        public String speak() {
            return "moo";
        }
    }

    static class Pig extends Animal {

        @Override
        public String speak() {
            return "oink";
        }
    }

    /**
     * TODO: Once you get the asserts to pass by plugging in the correct expectation,
     * TODO: refactor the code to use a supplier instead of polymorphism.
     * TODO: Delete the Cow and Pig sub-classes, then make Animal not abstract.
     * TODO: Have a constructor for animal that takes a speakFunction.  Use that function in the speak method
     */
    @Koan
    public void codeToRefactor() {

        Animal cow = new Cow();
        Animal pig = new Pig();
        assertEquals(__, cow.speak());
        assertEquals(__, pig.speak());
    }

}
