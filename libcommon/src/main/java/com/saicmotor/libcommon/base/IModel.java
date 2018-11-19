package com.saicmotor.libcommon.base;

/**
 * <pre>
 *     author : kuaipu
 *     e-mail : kuaipu@saicmotor.com
 *     time   : 06/11/2018
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public interface IModel {

    /**
     * 便于子类初始化
     */
    void onCreate();

    /**
     * 在框架中 {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    void onDestroy();
}
