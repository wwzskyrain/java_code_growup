package erik.mockito.demo;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnotationMockTest {

//  在Junit5中想用该注解，在需要
    @Mock
    private List<String> mockedList;

    @Test
    public void test_annotation_mock(){
        when(mockedList.size()).thenReturn(100);
        Assert.assertEquals(100,mockedList.size());
    }

}
