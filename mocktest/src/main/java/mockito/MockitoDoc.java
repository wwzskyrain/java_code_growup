package mockito;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoDoc {


    @Test
    public void verify_some_behaviour() {

        List mockList = mock(List.class);

        mockList.add("one");
        mockList.clear();

        verify(mockList).add("one");
        verify(mockList).clear();

    }

    @Test
    public void some_stubbing() {

        LinkedList mockList = mock(LinkedList.class);

        when(mockList.get(0)).thenReturn("first");

        when(mockList.get(0)).thenReturn("two");
//        when(mockList.get(1)).thenThrow(new RuntimeException("mockList.get(1)"));

        System.out.println(mockList.get(0));

        System.out.println(mockList.get(1));

        System.out.println(mockList.get(999));

        System.out.println(mockList.get(0));
        verify(mockList).get(1);
    }

    @Test
    public void argument_matcher() {





    }


}
