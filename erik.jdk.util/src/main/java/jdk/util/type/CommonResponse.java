package jdk.util.type;

public class CommonResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResponse() {
    }

    public CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static void main(String[] args) {


        CommonResponse response = new CommonResponse(101,"message","data");

        System.out.println(response);

    }

    public static CommonResponse returnWhat(){

        Long data = 123L;

        return new CommonResponse(101,"message",data);

    }

}
