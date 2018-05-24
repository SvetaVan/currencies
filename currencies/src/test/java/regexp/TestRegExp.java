package regexp;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestRegExp {
    @Test
    public void TelephoneRegTest() {
        String pattern =
                "^(\\+7|8)" +
                "(-\\d{3}-" + "|" +
                "\\(\\d{3}\\)"+"|"+
                "\\s\\d{3}\\s|" +
                "\\d{3})" +
                "[0-9]{3}[\\-\\s]?[0-9]{2}[\\-\\s]?[0-9]{2}$";
        Pattern pattern1 = Pattern.compile(pattern);
        {
            String string = "+7(925)064-87-67";
            Matcher matcher = pattern1.matcher(string);
            Assert.assertTrue("+7(925)064-87-67", matcher.find());
        }
        {
            String string = "8(925)064-87-67";
            Matcher matcher = pattern1.matcher(string);
            Assert.assertTrue("8(925)064-87-67", matcher.find());
        }
        {
            String string = "8-925-064-87-67";
            Matcher matcher = pattern1.matcher(string);
            Assert.assertTrue("8-925-064-87-67", matcher.find());
        }
        {
            String string = "8 925 064 87 67";
            Matcher matcher = pattern1.matcher(string);
            Assert.assertTrue("8 925 064 87 67", matcher.find());
        }
        {
            String string = "89250648767";
            Matcher matcher = pattern1.matcher(string);
            Assert.assertTrue("89250648767", matcher.find());
        }

    }
}
