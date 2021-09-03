import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {
    @Test
    public void emptyStringTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(0, calc.add(""));
    }
}
