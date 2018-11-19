package com.saicmotor.libcommon.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.saicmotor.libcommon.util.LogUtils;

import butterknife.ButterKnife;

/**
 * @author zyf
 * Created by zyf on 2018/10/9.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    protected final String TAG = this.getClass().getSimpleName();
    public BaseActivity mContext;
    protected P mPresenter;

    private Toast toast = null;

    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;


    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLodingError(String errMsg) {

    }


    @Override
    public void showShortToast(String msg) {
        toastShort(msg);
    }

    @Override
    public void showLongToast(String msg) {
        toastLong(msg);
    }

    /**
     * 显示长toast
     *
     * @param msg
     */
    public void toastLong(String msg) {
        if (null == toast) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);

        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }

    /**
     * 显示短toast
     *
     * @param msg
     */
    public void toastShort(String msg) {
        if (null == toast) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);

        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    private void logLifeCycle() {
        String method = Thread.currentThread().getStackTrace()[3].getMethodName();
        LogUtils.d(TAG, String.format("类名：%s,方法名:%s", getClass().getSimpleName(), method));
    }

    /**
     * 创建presenter实例
     */
    public abstract void createPresenter();

    /**
     * 获取布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化控件
     *
     */
    public abstract void initView();

    protected boolean allowFullScreen() {
        return false;
    }

    protected boolean immerseStatusbar() {
        return true;
    }

    protected boolean forcePortraitScreenOrientation() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (allowFullScreen()) {
            this.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (immerseStatusbar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 透明状态栏
                getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 透明导航栏
                getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }

        if (forcePortraitScreenOrientation()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (getLayoutResId() != 0) {
            mContextView = LayoutInflater.from(this)
                    .inflate(getLayoutResId(), null);
            setContentView(mContextView);
        }
        mContext = this;
        ButterKnife.bind(this);
        initView();
        logLifeCycle();
        createPresenter();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        logLifeCycle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logLifeCycle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        logLifeCycle();
    }

    @Override
    protected void onPause() {
        super.onPause();
        logLifeCycle();
    }

    @Override
    protected void onStop() {
        super.onStop();
        logLifeCycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        logLifeCycle();
    }
}
