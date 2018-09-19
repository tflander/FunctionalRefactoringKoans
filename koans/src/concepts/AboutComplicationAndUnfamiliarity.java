package concepts;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.function.Consumer;

public class AboutComplicationAndUnfamiliarity {

    Consumer<String> todo = Assert::fail;

    @Koan
    public void understandComplication() {
        todo.accept("In English, we often use the word 'complex' when we really mean 'unfamiliar'.\nWe are about to go through some concepts that are simple, but unfamiliar.\nBe aware that these concepts take time to absorb.\nOnce they become familiar, they can help you to simplify your code.\nDelete this line to continue.");
    }
}
