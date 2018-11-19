package com.saicmotor.libcommon.util.device;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by zyf on 2018/10/12.
 */
public class DensityUtils {

    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int dip2px(int dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int spToPx(int sp,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
