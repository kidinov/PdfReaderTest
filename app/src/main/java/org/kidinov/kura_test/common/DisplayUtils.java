package org.kidinov.kura_test.common;

import android.content.Context;

public class DisplayUtils {
    public static int dpToPx(Context context, float dp) {
        float scaleValue = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scaleValue + 0.5f);
    }
}
