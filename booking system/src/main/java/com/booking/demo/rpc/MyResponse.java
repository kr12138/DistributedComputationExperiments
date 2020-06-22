package com.booking.demo.rpc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyResponse<T> {
    private int status;
    private String msg;
    private T data;

    public final static int SUCCESS = 1;
    public final static int FAIL = 0;

    private MyResponse() {
    }

    public MyResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> MyResponse<T> success(){
        MyResponse<T> res = new MyResponse<>();
        res.setStatus(MyResponse.SUCCESS);
        return res;
    }

    public static <T> MyResponse<T> success(String msg){
        MyResponse<T> res = success();
        res.setMsg(msg);
        return res;
    }

    public static <T> MyResponse<T> success(T data){
        MyResponse<T> res = success();
        res.setData(data);
        return res;
    }

    public static <T> MyResponse<T> success(String msg, T data){
        MyResponse<T> res = success(msg);
        res.setData(data);
        return res;
    }

    public static <T> MyResponse<T> fail() {
        MyResponse res = new MyResponse<>();
        res.setStatus(MyResponse.FAIL);
        return res;
    }

    public static <T> MyResponse<T> fail(String msg){
        MyResponse<T> res = fail();
        res.setMsg(msg);
        return res;
    }
}
