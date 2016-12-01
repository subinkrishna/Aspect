package com.subinkrishna.aspect;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * @author Subinkrishna Gopi
 */
class LayoutHelper {

    /**
     * Initializes the {@link AspectRatioLayout}
     *
     * @param layout
     * @param attrs
     */
    static void init(AspectRatioLayout layout, AttributeSet attrs) {
        final Context context = (null != layout) ? layout.getContext() : null;
        if (null == context) {
            return;
        }

        TypedArray t = null;
        if (null != attrs) {
            t = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AspectRatioLayout, 0, 0);
        }

        if (null != t) {
            try {
                layout.lock(t.getInt(R.styleable.AspectRatioLayout_lock, AspectRatioLayout.WIDTH));
                layout.ratio(t.getFloat(R.styleable.AspectRatioLayout_ratio, 0));
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                t.recycle();
            }
        }
    }

    /**
     * Sets the dimensions based on ratio & locked measurement.
     *
     * @param layout
     */
    static AspectRatioLayout.Spec onMeasure(AspectRatioLayout layout) {
        if (null == layout)
            return null;

        final float ratio = layout.ratio();
        final int lock = layout.lock();
        if (ratio <= 0)
            return null;

        int width = layout.getMeasuredWidth();
        int height = layout.getMeasuredHeight();
        width = (lock == AspectRatioLayout.HEIGHT) ? (int) (height * ratio) : width;
        height = (lock == AspectRatioLayout.WIDTH) ? (int) (width / ratio) : height;
        return AspectRatioLayout.Spec.create(width, height);
    }
}
