package com.csz.assertor.Exception;

import com.csz.assertor.rest.ResultEnum;

public class OPException extends RuntimeException {

    private static final int DEFAULT_CODE = ResultEnum.INTERNAL_SERVER_ERROR.getCode();
    private int code =DEFAULT_CODE;

    public OPException(){}

    public OPException(String message){
            super(message);
    }

    public OPException(String message , Throwable cause){super(message,cause);}

    public OPException(String message, int code) {
        super(message);
        this.code = code;
    }

    public OPException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
