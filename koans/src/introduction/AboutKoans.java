package introduction;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.fail;

public class AboutKoans {

    @Koan
    public void findAboutKoansFile() {
        fail("delete this line to advance");
    }

    @Koan
    public void definitionOfKoanCompletion() {
        boolean koanIsComplete = false;
        if (!koanIsComplete) {
            fail("what if koanIsComplete variable was true?");
        }
    }

    @Koan
    public void updatingPlaceHolders() {
        // replace the double-underscore placeholder to make the following assertion true
        assertEquals("foo", __);
    }

}
