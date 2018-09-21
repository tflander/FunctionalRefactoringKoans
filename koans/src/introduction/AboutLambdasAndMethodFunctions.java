package introduction;

import com.sandwich.koan.Koan;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutLambdasAndMethodFunctions {

    public String uppercaseString(String input) {
        return input.toUpperCase();
    }

    static class Foo {

        static String repeatStarsStaticMethod(int count) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < count; ++i) {
                sb.append('*');
            }
            return sb.toString();
        }

        public String repeatStarsMethod(int count) {
            return Foo.repeatStarsStaticMethod(count);
        }

        static Function<Integer, String> repeatStarsLambda = count -> repeatStarsStaticMethod(count);
    }

    /**
     * We've been calling object methods for many years in Java.
     * Hopefully this is very familiar.
     */
    @Koan
    public void aboutMethods() {
        Foo myFoo = new Foo();
        assertEquals(__, myFoo.repeatStarsMethod(4));
    }

    /**
     * We've also been calling static methods for many years in Java.
     * Hopefully this is also very familiar.
     */
    @Koan
    public void aboutStaticMethods() {
        assertEquals(__, Foo.repeatStarsStaticMethod(4));
    }

    /**
     * Lambdas are new in Java8.  You can think of them as a different way to express a function.
     * Let's break down the three parts of this lambda:
     *
     *   count -> repeatStarsStaticMethod(count)
     *
     * We have and input variable called 'count', which is of some type.
     * Next is the dash-rocket '->'
     * Then we have the output or the code to produce the output 'repeatStarsStaticMethod(count)'
     *
     * input -> output
     */
    @Koan
    public void aboutLambdas() {
        UnaryOperator<String> doubleString = str -> str + str;
        assertEquals(__, doubleString.apply("foo"));
    }

    /**
     * Method references are kind of a bridge between objects and lambdas.  They provide a short-hand way to use
     * a method as a lamdba.
     */
    @Koan
    public void aboutMethodReferences() {
        Foo myFoo = new Foo();

        // Note the duplicate reference to 'count'
        Function<Integer, String> repeatStarsLambda = count -> myFoo.repeatStarsMethod(count);

        // The method reference is short-hand where the count is implied by the method signature.
        Function<Integer, String> repeatStarsMethodReference = myFoo::repeatStarsMethod;

        assertEquals(__, repeatStarsMethodReference.apply(4));
    }

    /**
     * We can also use method references for static methods.
     */
    @Koan
    public void aboutStaticMethodReferences() {

        // Note the duplicate reference to 'count'
        Function<Integer, String> repeatStarsLambda = count -> Foo.repeatStarsStaticMethod(count);

        // The method reference is short-hand where the count is implied by the method signature.
        Function<Integer, String> repeatStarsMethodReference = Foo::repeatStarsStaticMethod;

        assertEquals(__, repeatStarsMethodReference.apply(4));
    }

    /**
     * We can also use method references for our class instance using 'this'
     */
    @Koan
    public void aboutClassInstanceMethodReferences() {
        UnaryOperator<String> toUppercase = this::uppercaseString;

        assertEquals(__, toUppercase.apply("foo"));
    }

}
