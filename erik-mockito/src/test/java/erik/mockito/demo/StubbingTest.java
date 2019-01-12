package erik.mockito.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StubbingTest {

    @Test
    public void test_stub(){
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        Assert.assertEquals(mockedList.get(0),"first");


//        following throws runtime exception
//        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));

        verify(mockedList).get(999);

//      1.  assertThat() 相等怎么说？
//      2.  assert 异常？

    }

}
