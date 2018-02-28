package erik.construct.singleton;

/**
 * Created by nali on 2017/9/24.
 */
public class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
