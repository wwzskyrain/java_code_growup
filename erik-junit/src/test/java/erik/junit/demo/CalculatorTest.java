package erik.junit.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

// 这是junit4的例子
public class CalculatorTest {

    public static void main(String[] args) {

        Result result = new JUnitCore().runClasses(CalculatorTest.class);
        System.exit(result.wasSuccessful() ? 0 : 1);

    }

    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }
}