package com.saicmotor.libcommon.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saicmotor.libcommon.R;
import com.saicmotor.libcommon.R2;
import com.saicmotor.libcommon.util.device.DensityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zyf on 2018/6/28.
 */

public class BaseDialog extends Dialog {


    public static final int SETTING_BASE_DIALOG_POSITIVE_BUTTON_ID = 0;
    public static final int SETTING_BASE_DIALOG_NEGATIVE_BUTTON_ID = 1;

    BaseDialog me;
    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private boolean isCloseBtnEnable;
    private View view;
    private View layout;
    private TextView leftBtn, rightBtn;
    private TextView titleTv, messageTv;
    private FrameLayout contentFl;
    private ImageButton closeBtn;
    private LinearLayout backgroundLl;

    private String title;
    private String message;
    private String positiveLabel, negativeLabel;
    private String backgroundColor;
    private int backgroundImgId = 0;
    private String titleColor;
    private String messageColor;

    private OnDialogBtnClickListener leftBtnListener, rightBtnListener;
    private boolean isLeftBtnEnable = true;
    private boolean isRightBtnEnable = true;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.common_base_dialog_left_btn) {
                if (!isLeftBtnEnable) {
                    return;
                }
                me.dismiss();
                if (leftBtnListener == null) {
                    return;
                }
                leftBtnListener.onClick(SETTING_BASE_DIALOG_NEGATIVE_BUTTON_ID);
            } else if (v.getId() == R.id.common_base_dialog_right_btn) {
                if (!isRightBtnEnable) {
                    return;
                }
                me.dismiss();
                if (rightBtnListener == null) {
                    return;
                }
                rightBtnListener.onClick(SETTING_BASE_DIALOG_POSITIVE_BUTTON_ID);
            } else if(v.getId() == R.id.common_base_dialog_close_btn) {
                me.dismiss();
            }
        }
    };

    private BaseDialog(Builder builder) {
        this(builder, R.style.CommonBaseDialog);
    }

    private BaseDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        me = this;
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        isCloseBtnEnable = builder.isCloseBtnEnable;
        view = builder.view;
        title = builder.title;
        message = builder.message;
        backgroundColor = builder.backgroundColor;
        backgroundImgId = builder.backgroundImgId;
        titleColor = builder.titleColor;
        messageColor = builder.messageColor;
        positiveLabel = builder.positiveLabel;
        negativeLabel = builder.negativeLabel;
        leftBtnListener = builder.leftBtnListener;
        rightBtnListener = builder.rightBtnListener;
    }

    public void setTitle(String title) {
        titleTv.setText(title);
        titleTv.setVisibility(View.VISIBLE);
    }

    public void setMessage(String message) {
        messageTv.setText(message);
        messageTv.setVisibility(View.VISIBLE);
    }

    public void setPositiveButton(String label) {
        rightBtn.setText(label);
        rightBtn.setVisibility(View.VISIBLE);
    }

    public void setNegativeButton(String label) {
        leftBtn.setText(label);
        leftBtn.setVisibility(View.VISIBLE);
    }

    public void setPositiveButtonEnable(boolean enable) {
        if (enable) {
            rightBtn.setTextColor(Color.parseColor("#FF009688"));
            isRightBtnEnable = true;
        } else {
            rightBtn.setTextColor(Color.parseColor("#727272"));
            isRightBtnEnable = false;
        }
    }

    public void setNegativeButtonEnable(boolean enable) {
        if (enable) {
            leftBtn.setTextColor(Color.parseColor("#FF009688"));
            isLeftBtnEnable = true;
        } else {
            leftBtn.setTextColor(Color.parseColor("#727272"));
            isLeftBtnEnable = false;
        }
    }

    public void isCloseBtnEnable(boolean isEnable) {
        if(closeBtn == null) {
            return;
        }
        isCloseBtnEnable = isEnable;
        if(isCloseBtnEnable) {
            closeBtn.setVisibility(View.VISIBLE);
        } else {
            closeBtn.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = LayoutInflater.from(context).inflate(R.layout.common_base_dialog, null);
        contentFl = layout.findViewById(R.id.common_base_dialog_content);
        titleTv = layout.findViewById(R.id.common_base_dialog_title);
        messageTv = layout.findViewById(R.id.common_base_dialog_message);
        leftBtn = layout.findViewById(R.id.common_base_dialog_left_btn);
        rightBtn = layout.findViewById(R.id.common_base_dialog_right_btn);
        closeBtn = layout.findViewById(R.id.common_base_dialog_close_btn);
        backgroundLl = layout.findViewById(R.id.common_base_dialog_background);

        leftBtn.setOnClickListener(onClickListener);
        rightBtn.setOnClickListener(onClickListener);
        closeBtn.setOnClickListener(onClickListener);

        titleTv.setVisibility(View.GONE);
        messageTv.setVisibility(View.GONE);
        leftBtn.setVisibility(View.GONE);
        rightBtn.setVisibility(View.GONE);
        closeBtn.setVisibility(View.GONE);

        if (title != null) {
            setTitle(title);
        }

        if (message != null) {
            setMessage(message);
        }

        if (positiveLabel != null && rightBtnListener != null) {
            setPositiveButton(positiveLabel);
        }

        if (negativeLabel != null) {
            setNegativeButton(negativeLabel);
        }

        if (view != null) {
            contentFl.addView(view);
        }

        if (height == 0) {
            height = DensityUtils.dip2px(240);
        }
        if (width == 0) {
            width = DensityUtils.dip2px(460);
        }

        setContentView(layout);

        setCanceledOnTouchOutside(cancelTouchout);

        isCloseBtnEnable(isCloseBtnEnable);
        if(backgroundColor != null) {
            backgroundLl.setBackgroundColor(Color.parseColor(backgroundColor));
        }

        if(backgroundImgId != 0) {
            backgroundLl.setBackgroundResource(backgroundImgId);
        }

        if(titleColor != null) {
            titleTv.setTextColor(Color.parseColor(titleColor));
        }

        if(messageColor != null) {
            messageTv.setTextColor(Color.parseColor(messageColor));
        }

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
    }

    public static final class Builder {

        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private boolean isCloseBtnEnable;
        private View view;
        private int resStyle = -1;
        private String title;
        private String message;
        private String positiveLabel, negativeLabel;
        private String backgroundColor;
        private String titleColor;
        private String messageColor;
        private int backgroundImgId = 0;
        private OnDialogBtnClickListener leftBtnListener, rightBtnListener;

        private BaseDialog dialog;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * 设置标题的颜色
         * @param color
         * @return
         */
        public Builder setTitleColor(String color) {
            titleColor = color;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置内容的颜色
         * @param color
         * @return
         */
        public Builder setMessageColor(String color) {
            this.messageColor = color;
            return this;
        }

        public Builder setPositiveButton(String label, OnDialogBtnClickListener listener) {
            this.positiveLabel = label;
            this.rightBtnListener = listener;
            return this;
        }

        public Builder setNegativeButton(String label, OnDialogBtnClickListener listener) {
            this.negativeLabel = label;
            this.leftBtnListener = listener;
            return this;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder setLayout(View view) {
            this.view = view;
            return this;
        }

        /**
         * 设置整个弹框的背景色
         * @param color 背景色，如"#ffffff"
         * @return
         */
        public Builder setBackgroundColor(String color) {
            backgroundColor = color;
            return this;
        }

        /**
         * 设置整个弹框的背景图
         * @param resImg
         * @return
         */
        public Builder setBackgroundImg(int resImg) {
            backgroundImgId = resImg;
            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }

        public Builder heightdp(int val) {
            height = DensityUtils.dip2px(val);
            return this;
        }

        public Builder widthdp(int val) {
            width = DensityUtils.dip2px(val);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        /**
         * 是否支持点击弹框外部消失
         * @param val
         * @return
         */
        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        /**
         * 是否展示标题栏关闭按钮x
         * @param isEnable
         * @return
         */
        public Builder isCloseBtnEnable(boolean isEnable) {
            isCloseBtnEnable = isEnable;
            return this;
        }


        public BaseDialog build() {
            if (resStyle != -1) {
                dialog = new BaseDialog(this, resStyle);
            } else {
                dialog = new BaseDialog(this);
            }
//            dialog.create();
            return dialog;
        }
    }

    public interface OnDialogBtnClickListener {
        void onClick(int id);
    }
}
