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

    static class Animal {
        private final Supplier<String> speakFunction;

        public Animal(Supplier<String> speakFunction) {
            this.speakFunction = speakFunction;
        }

        public String speak() {
            return speakFunction.get();
        };
    }

    @Koan
    public void codeToRefactor() {
        Animal cow = new Animal(() -> "moo");
        Animal pig = new Animal(() -> "oink");
        assertEquals("moo", cow.speak());
        assertEquals("oink", pig.speak());
    }

}
