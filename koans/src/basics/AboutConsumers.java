package basics;

import com.sandwich.koan.Koan;
import com.sandwich.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

/**
 * Consumers take input and return nothing.  They are good indicators that their purpose is to
 * perform some side-effect, such as popping a message onto a queue or saving data.  Pure functions
 * do not have side-effects, but real systems need to interact with the outside world.
 */
public class AboutConsumers {


    private String lastLoggedMessage;

    /**
     */
    @Koan
    public void consumeLogMessage() {

        Consumer<String> messageLogger = message -> {
            lastLoggedMessage = __;
        };

        messageLogger.accept("testing");
        assertEquals("testing", lastLoggedMessage);
    }

}

