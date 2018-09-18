package basics;

import com.sandwich.koan.Koan;

import java.util.function.Function;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutFunctions {

    /**
     * The Function<T, R> interface templates a function that takes an object of type T and returns a result of type R
     *
     * This is good (among other things) for creating mapping and translation functions.
     */
    @Koan
    public void translationFunction() {
        DataFromDatabase dataFromDatabase = new DataFromDatabase(123L, "AcctNo", "Bob");
        DataDomainObject expectedDomainObject = new DataDomainObject(123L, "AcctNo", "Bob");


        Function<DataFromDatabase, DataDomainObject> dataBaseToDomainObject = fromDatabase -> {
            // TODO: write the right-hand-side of the conversion function using the input variable 'fromDatabase'
            return new DataDomainObject(fromDatabase.getDataIdentifierSeq(), __, __);
        };

        DataDomainObject actualDomainObject = dataBaseToDomainObject.apply(dataFromDatabase);
        assertEquals(123L, actualDomainObject.getDatabaseKey());
        assertEquals("AcctNo", actualDomainObject.getAccountNumber());
        assertEquals("Bob", actualDomainObject.getCustomerName());
    }

}
