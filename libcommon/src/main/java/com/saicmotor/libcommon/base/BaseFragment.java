package com.saicmotor.libcommon.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zyf on 2018/10/9.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IView {

    public T mPresenter;
    public View mRootView;
    protected Context mContext;
    private Unbinder unbinder;
    private Toast toast;

    /**
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            if (getLayoutResId() != 0) {
                mRootView = LayoutInflater.from(mContext)
                        .inflate(getLayoutResId(), container, false);
                unbinder = ButterKnife.bind(this, mRootView);
                initView(savedInstanceState);
                createPresenter();
            } else {
                throw new RuntimeException("layoutResID==-1 have u create your layout?");
            }
        }
        return mRootView;
    }

    @Override
    public void showShortToast(String msg) {
        if (mContext == null) {
            return;
        }
        if (null == toast) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }

    @Override
    public void showLongToast(String msg) {
        if (mContext == null) {
            return;
        }
        if (null == toast) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }


    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLodingError(String errMsg) {

    }

    /**
     * 创建presenter实例
     */
    public abstract void createPresenter();

    /**
     * 初始化
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 传入布局文件
     *
     * @return
     */
    public abstract int getLayoutResId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//页面销毁网络请求也取消
        }
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();


    }
}
