package com.saicmotor.libcommon.base;

/**
 * Created by zyf on 2018/10/10.
 */

public interface ICommonManager {
    String version = "1.0";

    String getVersion();

    /**
     * init byod
     */
    void initByod();


    /**
     * clear the instance of CommonManager
     */
    void clear();
}
