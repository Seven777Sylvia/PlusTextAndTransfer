package com.sevenqiu.plustextandtransfer.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @author Seven Qiu
 * @date 2019-04-25
 */
public class Util {

    public static float dip2Pixl(float dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, Resources.getSystem().getDisplayMetrics());
    }

    public static float sp2Pixl(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static float getCameraZ(){
        return -6*Resources.getSystem().getDisplayMetrics().density;
    }
}
