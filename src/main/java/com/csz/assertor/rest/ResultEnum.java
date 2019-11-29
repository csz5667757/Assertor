package com.csz.assertor.rest;

/**
 * restful返回公共消息枚举
 */
public enum ResultEnum {

    SUCCESS(200,"success"),
    INTERNAL_SERVER_ERROR(-1,"未知异常，请联系管理员"),
    BAD_REQUEST(400,"请求参数错误"),
    NOT_FOUND(404,"路径不存在，请检查路径是否正确"),
    TABLESUCC(0,"success")
    ;

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
