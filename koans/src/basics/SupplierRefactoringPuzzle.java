package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.fail;

public class SupplierRefactoringPuzzle {

    Consumer<String> todo = Assert::fail;

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

    @Koan
    public void codeToRefactor() {
        todo.accept("Refactor the code to use a supplier instead of polymorphism.\nDelete the Cow and Pig sub-classes, then make Animal not abstract.\nHave a constructor for animal that takes a speakFunction.  Use that function in the speak method.\nDon't forget to delete this 'todo' line.");
        Animal cow = new Cow();
        Animal pig = new Pig();
        assertEquals("moo", cow.speak());
        assertEquals("oink", pig.speak());
    }

}
