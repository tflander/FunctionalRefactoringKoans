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

        Supplier<String> cowSpeak = () -> __;
        assertEquals("moo", cowSpeak.get());
    }

    /**
     * The above example seems like an unnecessary level of indirection.  You don't need a string supplier
     * when you can simply provide a string.  While this is true, the supplier allows you to supply a string
     * based on more complex logic.
     */
    @Koan
    public void supplyComplexSpeakFunction() {
        Supplier<String> excitedCowSpeak = () -> __;
        Assert.assertTrue(excitedCowSounds.contains(excitedCowSpeak.get()));

        // HINT:
        Supplier<String> methodReference = this::excitedCowSpeak;
    }

    private String excitedCowSpeak() {
        int randomSound = new Double(Math.random() * excitedCowSounds.size()).intValue();
        return excitedCowSounds.get(randomSound);
    }

}

/*
        Animal cow = new Animal(() -> "moo");
        assertThat(cow.speak()).isEqualTo("moo");

        Animal excitedCow = new Animal(() -> {
            double random = Math.random();
            if(random < .1) return "moo!";
            else if(random < .3) return "moo moo!";
            else if(random < .5) return "MOO!";
            else if(random < .7) return "MOO MOO!";
            else return "MOOOOO!!";
        });

        assertThat(excitedCow.speak()).matches("moo!|moo moo!|MOO!|MOO MOO!|MOOOOO!!");
    }
}

class Animal {
    private final Supplier<String> speakSupplier;

    Animal(Supplier<String> speakSupplier) {
        this.speakSupplier = speakSupplier;
    }

    public String speak() {
        return "TODO: use speakSupplier get the string to return";
    }

}

 */