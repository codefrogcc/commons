package com.jcl.pbcms.util.result;

public class CodeMsg {

    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(200, "成功");
    public static CodeMsg FAIL = new CodeMsg(400,"失败");
    public static CodeMsg SERVER_ERROR = new CodeMsg(400, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(400, "参数校验异常：%s");

    //登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(504, "Session不存在或者已经失效");

    public static CodeMsg INFO(int code, String msg){
        return new CodeMsg(code,msg);
    };

    private CodeMsg() {
    }

    private CodeMsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }

}
