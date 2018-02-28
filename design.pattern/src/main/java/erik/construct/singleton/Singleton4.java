package erik.construct.singleton;

/**
 * Created by nali on 2017/9/24.
 */
public class Singleton4 {
    private static Singleton4 instance=new Singleton4();
    private Singleton4(){

    }
    public static Singleton4 getInstance(){
        return instance;
    }
}

