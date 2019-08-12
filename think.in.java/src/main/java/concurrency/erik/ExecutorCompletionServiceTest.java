package concurrency.erik;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author erik.wang
 * @date 2019/03/25
 **/
public class ExecutorCompletionServiceTest {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<Integer> ecs
                = new ExecutorCompletionService<Integer>(executorService);

        List<Callable<Integer>> solvers = getCallable();

        for (Callable<Integer> s : solvers)
            ecs.submit(s);
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            Integer result = null;
            try {
                result = ecs.take().get();
                if (result != null) {
                    System.out.printf("i=%d result=%d\n", i, result);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }

        }

    }

    public static List<Callable<Integer>> getCallable() {

        return IntStream.range(1, 10).mapToObj(i -> new Callable<Integer>() {
            @Override
            public Integer call() {
                try {
                    TimeUnit.SECONDS.sleep(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i;
            }
        }).collect(Collectors.toList());


    }

}
