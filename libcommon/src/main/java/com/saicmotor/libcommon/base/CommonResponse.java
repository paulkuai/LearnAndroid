package com.saicmotor.libcommon.base;

/**
 * <pre>
 *     author : kuaipu
 *     e-mail : kuaipu@saicmotor.com
 *     time   : 2018/03/15
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class CommonResponse {


    /**
     * errorCode : 0
     * data : {"albumId":321787}
     */

    private int errorCode;
    private String data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
