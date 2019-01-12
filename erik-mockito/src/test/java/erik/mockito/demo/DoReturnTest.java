package erik.mockito.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;




/**
 *  doReturn()|doThrow()|doAnswer()|doNothing()|doCallRealMethod()family of methods
 *  <br/> 输出前置-erik；其实没太多必要
 *  <br/>  使用场景：
 *  <br/> 1.void类型的函数
 *  <br/> 2.用于抛异常，等效于 when().thenThrow();
 *  <br/> 3.用于spy对象调用比抛异常的函数时；因为此时会被异常打断
 */
public class DoReturnTest {

    @Test
    public void test_do_return_and_do_throw(){

        List<String> mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

    }

}
