package com.saicmotor.libcommon.base;

import com.saicmotor.libcommon.util.http.HttpUtils;

/**
 * <pre>
 *     @author : kuaipu
 *     e-mail : kuaipu@saicmotor.com
 *     time   : 06/11/2018
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class BaseModel implements IModel {

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        HttpUtils.getInstance().cancel(TAG);
    }
}
