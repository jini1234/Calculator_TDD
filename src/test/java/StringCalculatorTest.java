import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("negatives not allowed:-2");
        StringCalculator calc = new StringCalculator();
        calc.Add("//;\n1;-2");
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

    @Test
    public void ignoreBiggerThanThousandTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(2, calc.Add("2,1001"));
    }

    @Test
    public void multipleLengthDelimiterTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(6, calc.Add("//[+++]\n1+++2+++3"));
        Assert.assertEquals(6, calc.Add("//[+;&]\n1+;&2+;&3"));
    }

    @Test
    public void multipleDelimiterTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(6, calc.Add("//[+][%]\n1+2%3"));
    }

    @Test
    public void multiDelimiterTest() {
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(6, calc.Add("//[++][%%]\n1++2%%3"));
    }

    @Test
    public void addTestForMultiplication(){
        StringCalculator calc = new StringCalculator();
        Assert.assertEquals(4, calc.Add("//[*]\n1*2*2"));
        Assert.assertEquals(12, calc.Add("//[**][%%]\n2**2%%3"));
        Assert.assertEquals(12, calc.Add("//[***]\n2***2***3"));
        Assert.assertEquals(12, calc.Add("//[*;&]\n2*;&2*;&3"));
    }
}

//“//[*]\n1*2*2” should return 4.“//[***]\n1***2***3” should return 6“//[***]\n1***6***3” should return 18

