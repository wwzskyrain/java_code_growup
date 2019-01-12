package erik.mockito.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// verify 似乎没太大用处；
public class VerifyTest {


    @Test
    public void test_verify_some_behavior() {

        List<String> mockedList = mock(ArrayList.class);

        mockedList.add("one");
        mockedList.clear();

//      验证"add("one")"执行过
        verify(mockedList).add("one");
        verify(mockedList).clear();


//      会报错：
//        Wanted but not invoked:
//        arrayList.size();
        verify(mockedList).size();


    }

}
