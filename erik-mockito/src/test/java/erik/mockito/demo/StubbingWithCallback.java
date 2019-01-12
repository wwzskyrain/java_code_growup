package erik.mockito.demo;


import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubbingWithCallback {

    @Test
    public void test_stubbing_with_a_callback() {

        List<String> mockedList = mock(ArrayList.class);

//      when(mock.something()).then(answer);
        when(mockedList.get(100)).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                return "called with arguments: " + Arrays.toString(arguments);

            }
        });

        System.out.println(mockedList.get(100));
        System.out.println(mockedList.get(1));

    }

}
