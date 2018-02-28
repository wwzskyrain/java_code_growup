package runner.suit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test01 {


    @Test
    public void test_01(){

        assertEquals("failure - strings are not equal", "text", "text");
    }

}
