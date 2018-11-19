package com.saicmotor.libcommon.util;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.AppUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class LogUtils {


    public static void init() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                // (Optional) Whether to show thread info or not. Default true
                .showThreadInfo(true)
                // (Optional) How many method line to show. Default 2
                .methodCount(2)
                // (Optional) Hides internal method calls up to offset. Default 5
                .methodOffset(1)
                // (Optional) Changes the log strategy to print out. Default LogCat
                //.logStrategy(customLog)
                // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .tag("BYOD")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return AppUtils.isAppDebug();
            }
        });
    }

    public static void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    public static void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    public static void w(String tag, String msg) {
        Logger.t(tag).w(msg);
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    public static void v(String msg) {
        Logger.v(msg);
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void w(String msg) {
        Logger.w(msg);
    }

    public static void e(String msg) {
        Logger.e(msg);
    }


}
