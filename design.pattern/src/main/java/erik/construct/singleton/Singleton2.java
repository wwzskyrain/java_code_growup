package erik.construct.singleton;

/**
 * Created by nali on 2017/9/24.
 */
public class Singleton2 {
    private static Singleton2 instance=null;
    private Singleton2(){

    }
    public static synchronized Singleton2 getInstance(){
        if(instance==null){
            instance=new Singleton2();
        }
        return instance;
    }
}
