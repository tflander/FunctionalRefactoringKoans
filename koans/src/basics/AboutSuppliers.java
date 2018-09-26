package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutSuppliers {

    private final List<String> excitedCowSounds = Arrays.asList(
            "moo!", "moo moo!", "MOO!", "MOO MOO!", "MOOOOO!!"
    );

    /**
     * Functions require an input parameter, but sometimes we don't need one.  That's what suppliers are for
     */
    @Koan
    public void supplySpeakFunction() {

        excitedCowSpeak();

        Supplier<String> cowSpeak = () -> "moo";
        assertEquals("moo", cowSpeak.get());
    }

    /**
     * The above example seems like an unnecessary level of indirection.  You don't need a string supplier
     * when you can simply provide a string.  While this is true, the supplier allows you to supply a string
     * based on more complex logic.
     */
    @Koan
    public void supplyComplexSpeakFunction() {
        Supplier<String> excitedCowSpeak = this::excitedCowSpeak;
        Assert.assertTrue(excitedCowSounds.contains(excitedCowSpeak.get()));
    }

    private String excitedCowSpeak() {
        int randomSound = new Double(Math.random() * excitedCowSounds.size()).intValue();
        return excitedCowSounds.get(randomSound);
    }

}
