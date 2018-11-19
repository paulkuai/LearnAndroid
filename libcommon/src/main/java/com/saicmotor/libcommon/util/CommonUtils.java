package com.saicmotor.libcommon.util;

import android.view.View;

/**
 * <pre>
 *     @author : kuaipu
 *     e-mail : kuaipu@saicmotor.com
 *     time   : 06/11/2018
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class CommonUtils {

    /**
     * 封装findViewById方法
     *
     * @param resId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T $(View view, int resId) {
        return (T) view.findViewById(resId);
    }
}
