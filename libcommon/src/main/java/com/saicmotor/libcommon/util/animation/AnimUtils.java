package com.saicmotor.libcommon.util.animation;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by zyf on 2018/10/9.
 */

public class AnimUtils {
    public static void showUp(View view) {
        ObjectAnimator.ofFloat(view, "alpha", 0, 1)
                .setDuration(500).start();
    }
}
