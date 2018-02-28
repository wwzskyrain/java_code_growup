package erik.construct.singleton;

/**
 * Created by nali on 2017/9/24.
 */
public class Singleton1 {

        private static Singleton1 instance=null;
        private Singleton1(){

        }
        public static Singleton1 getInstance(){
            if(instance==null){
                instance=new Singleton1();
            }
            return instance;
        }

}
