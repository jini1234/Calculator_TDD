import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {
    @Test
    public void emptyStringTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(0, calc.Add(""));
    }

    @Test
    public void oneInputTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(1, calc.Add("1"));
    }

    @Test
    public void twoInputTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(3, calc.Add("1,2"));
    }

    /** Test for unknown amount of numbers */
    @Test
    public void NInputTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(10, calc.Add("1,2,3,4"));
    }

    @Test
    public void withNewLineTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(10, calc.Add("1\n2,3\n4"));
    }

    @Test
    public void otherDelimitersTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(3, calc.Add("//;\n1;2"));
        Assert.assertEquals(6, calc.Add("//;\n1;2\n3"));
    }

    @Test
    public void negNumberTest() {
        StringCalculator calc = new StringCalculator();
        try {
            calc.Add("//;\n1;-2");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("negatives not allowed:-2", e.getMessage());
        }
    }

    @Test
    public void multipleNegNumberTest() {
        StringCalculator calc = new StringCalculator();
        try {
            calc.Add("//;\n1;-2;-3");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("negatives not allowed:-2,-3", e.getMessage());
        }
    }

    @Test
    public void getCalledCountTest() {
        StringCalculator calc = new StringCalculator();
        calc.Add("1");
        calc.Add("1,2");
        Assert.assertEquals(2, calc.GetCalledCount());
    }
}
