package erik.mockito.demo;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

//  spy是一个"partial mock"的概念。
public class SpyTest {

    @Test
    public void test_spy(){

        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

    }


}
