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
    private String lastTextMessage;
    private String lastEmailMessage;
    private String lastTweet;

    /**
     * Here is a simple example where we are sending a message to a logger
     */
    @Koan
    public void consumeLogMessage() {

        Consumer<String> messageLogger = message -> {
            lastLoggedMessage = __;
        };

        messageLogger.accept("testing");
        assertEquals("testing", lastLoggedMessage);
    }

    /**
     * Like our first Supplier example, the previous example uses an unnecessary level of indirection.
     * We don't need a consumer when all we need to do is pass a string to a logger.
     *
     * Let's add some complication so using a supplier makes sense.
     *
     * Let's create a message dispatcher to dispatch messages to some group of delivery methods, e.g.
     * text, email, and tweet.
     */
    @Koan
    public void dispatchMessage() {
        Consumer<String> messageDispatcher = message -> {
            lastTextMessage = "Text: " + __;
            lastEmailMessage = "Email: " + __;
            lastTweet = "Tweet: " + __;
        };

        messageDispatcher.accept("testing");
        assertEquals("Text: testing", lastTextMessage);
        assertEquals("Email: testing", lastEmailMessage);
        assertEquals("Tweet: testing", lastTweet);
    }

    /**
     * The above example may still look a little strange, because all we really needed to do was to
     * write a dispatcher method that takes a string and calls the three hard-coded delivery methods.
     * Let's make the delivery methods injectable to show the power of functional programming.
     */
    @Koan
    public void flexibleDispatcher() {
        List<Consumer<String>> deliveryMethods = Arrays.asList(
                message -> lastTextMessage = "New Text: " + message,
                message -> lastEmailMessage = "New Email: " + message,
                message -> lastTweet = "New Tweet: " + message
        );

        deliveryMethods.forEach(deliveryMethod -> deliveryMethod.accept("new message"));

        assertEquals("New Text: new message", lastTextMessage);
        assertEquals("New Email: new message", lastEmailMessage);
        assertEquals("New Tweet: new message", lastTweet);
    }

}

