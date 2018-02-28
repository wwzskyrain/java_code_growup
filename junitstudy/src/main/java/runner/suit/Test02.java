package runner.suit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test02 {


    @Test
    public void test02(){
        assertEquals("failure - strings are not equal", "text", "text");
    }

}
