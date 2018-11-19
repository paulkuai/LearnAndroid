package com.saicmotor.libcommon.base;

/**
 * <pre>
 *     @author : kuaipu
 *     e-mail : kuaipu@saicmotor.com
 *     time   : 06/11/2018
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    protected final String TAG = this.getClass().getSimpleName();

    protected M mModel;
    protected V mView;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param view
     */
    public BasePresenter(M model, V view) {
        this.mModel = model;
        this.mView = view;
        this.mModel.onCreate();
        onCreate();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param view
     */
    public BasePresenter(V view) {
        this.mView = view;
        onCreate();
    }


    /**
     * 初始化操作，可选
     */
    @Override
    public void onCreate() {
    }

    /**
     * 在框架中 {@link BaseActivity#onDestroy()} 时会默认调用 {@link IPresenter#onDestroy()}
     */
    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
        }
    }
}