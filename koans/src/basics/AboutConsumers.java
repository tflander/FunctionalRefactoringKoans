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
                message -> lastTextMessage = "New Text: " + __,
                message -> lastEmailMessage = "New Email: " + __,
                message -> lastTweet = "New Tweet: " + __
        );

        deliveryMethods.forEach(deliveryMethod -> deliveryMethod.accept("new message"));

        assertEquals("New Text: new message", lastTextMessage);
        assertEquals("New Email: new message", lastEmailMessage);
        assertEquals("New Tweet: new message", lastTweet);
    }

    /**
     * Now that we have a simple method to dispatch a message to a list of delivery method consumers,
     * let's refactor out the redundant code for creating the consumers.  In real life, our delivery methods
     * might be very different, making code re-use impossible.  The purpose of this step is to show that
     * we can construct functions similar to how we construct objects.  We will dig deeper on this idea
     * when we explore currying.
     */
    public void refactoredDispatcher() {
        List<Consumer<String>> deliveryMethods = Arrays.asList(
                message -> lastTextMessage = "Awesome Text: " + __,
                message -> lastEmailMessage = "Awesome Email: " + __,
                message -> lastTweet = "Awesome Tweet: " + __,
                deliver("Text", lastTextMessage)
        );

        deliveryMethods.forEach(deliveryMethod -> deliveryMethod.accept("message"));

        assertEquals("Awesome Text: message", lastTextMessage);
        assertEquals("Awesome Email: message", lastEmailMessage);
        assertEquals("Awesome Tweet: message", lastTweet);
    }

    private Consumer<String> deliver(String deliveryMethod, String fieldName) {
        return message -> {
            String messageToSend = "Awesome " + deliveryMethod + ": " + message;
        };
    }
}

