package erik.mockito.demo;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


//  如何定义/描述:"参数匹配器"，可参见'http://static.javadoc.io/org.mockito/mockito-core/2.23.4/org/mockito/ArgumentMatchers.html'
//  似乎 argumentMatcher的意义也不是很大
//  当心啊：If you are using argument matchers, all arguments have to be provided by matchers.
public class StubWithArgumentMatcher {

    @Test
    public void test_stub_with_argument_matcher() {

        List<String> mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenReturn("element");

        for (int i = 0; i < 10; i++) {
            System.out.printf("call get(%d) return:%s \n", i - 5, mockedList.get(i - 5));
        }


//      自定义ArgumentMatcher并且使用它
        when(mockedList.addAll(argThat(new ListOfTwoElements()))).thenReturn(true);
        System.out.println(mockedList.addAll(Arrays.asList("one", "two")));
        verify(mockedList).addAll(argThat(new ListOfTwoElements()));


    }

    public static class ListOfTwoElements implements ArgumentMatcher<List> {

        @Override
        public boolean matches(List argumentList) {
            return argumentList.size() == 2;
        }
    }


}
