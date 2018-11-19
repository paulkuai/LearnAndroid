package com.saicmotor.libcommon.util.device;

import android.content.Context;
import android.util.DisplayMetrics;

import com.saicmotor.libcommon.util.LogUtils;
import com.saicmotor.libcommon.util.TipUtils;

/**
 * Created by zyf on 2018/10/9.
 */

public class DeviceUtils {
    private static final String TAG = "DeviceUtils";

    /**
     * 展示当前设备的实际屏幕分辨率，分别中日志中输入以及弹出toast提示。仅用于查看
     * @param context
     */
    public static void showPhoneSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        LogUtils.v(TAG, "phone size is " + width + "x" + height);
        TipUtils.showToast(context, "phone size is "+width+"x"+height);
    }
}
