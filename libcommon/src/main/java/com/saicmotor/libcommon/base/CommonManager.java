package com.saicmotor.libcommon.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.saicmotor.libcommon.util.LogUtils;
import com.saicmotor.libcommon.util.http.HttpUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by zyf on 2018/10/10.
 */

public class CommonManager implements com.saicmotor.libcommon.base.ICommonManager {

    private static ICommonManager instance;
    Application mApp;

    private CommonManager(Application context) {
        mApp = context;
    }

    public static ICommonManager getInstance(Application app) {
        if (instance == null) {
            instance = new CommonManager(app);
        }
        return instance;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void initByod() {
        Utils.init(mApp);

        HttpUtils.getInstance().init(mApp);

        LogUtils.init();
        if (AppUtils.isAppDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(mApp);

        CrashReport.initCrashReport(mApp, "d10cd7fd98", false);
    }

    @Override
    public void clear() {
        instance = null;
    }
}
