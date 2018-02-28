package jdk.util.exception;

/**
 * Created by nali on 2017/10/31.
 */
public class CatchRuntimeException {


    public static void main(String[] args) {

        CatchRuntimeException catchRuntimeException=new CatchRuntimeException();
        try{
            catchRuntimeException.throwRuntimeException();
        }catch (Exception e){
            System.out.println("catch runtimeException success. e.message ="+e.getMessage());
        }



    }

    public void throwRuntimeException(){

        throw new RuntimeException("throw exception!");

    }

}
