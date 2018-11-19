package com.saicmotor.libcommon.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.saicmotor.libcommon.base.BaseDialog;

/**
 * Created by zyf on 2018/10/12.
 */
public class TipUtils {

    /**
     * 根据内容显示提示信息
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text){
        if (context == null) {
            return;
        }
        Toast toast = null;
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 弹出确认提示框
     * @param context
     * @param tip
     */
        public static void showTips(Context context, String tip) {
            new BaseDialog.Builder(context).setTitle("提示").setMessage(tip).
                    setPositiveButton("确定", new BaseDialog.OnDialogBtnClickListener() {

                        @Override
                        public void onClick(int id) {

                        }
                    }).build().show();
    }
}
