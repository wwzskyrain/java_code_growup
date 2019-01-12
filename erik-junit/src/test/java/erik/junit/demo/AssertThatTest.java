package erik.junit.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

public class AssertThatTest {

    @Test
    public void test_assert_that(){

        Calculator calculator = new Calculator();

        int result= calculator.evaluate("1+2+3");
//      首先assertThat是junit4的方法，junit5中已经不带这个方法了
//      其次assertThat的第二个参数'Matcher<? super T> matcher'是hamcrest的matcher，不是junit的。
//      第三，各种matcher的构造见类'CoreMatchers'
        Assert.assertThat(result,is(6));

    }

}
