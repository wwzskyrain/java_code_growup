package erik.java8;

/**
 * Created by nali on 2017/9/24.
 */

@FunctionalInterface
public interface Converter<F, T> {

    T convert(F from);

}
