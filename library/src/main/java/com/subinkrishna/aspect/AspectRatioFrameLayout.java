package com.subinkrishna.aspect;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author Subinkrishna Gopi
 */
public class AspectRatioFrameLayout extends FrameLayout implements AspectRatioLayout {

    // Log tag
    private static final String TAG = "AspectRatioFrameLayout";

    private int lock = AspectRatioLayout.WIDTH;
    private float ratio = 0;

    public AspectRatioFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutHelper.init(this, attrs);
    }

    @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Spec spec = LayoutHelper.onMeasure(this);
        if (null != spec) {
            setMeasuredDimension(spec.width, spec.height);
        }
    }

    // AspectRatioLayout

    @Override public AspectRatioLayout ratio(float ratio) {
        if (this.ratio != ratio) {
            this.ratio = ratio;
            requestLayout();
        }
        return this;
    }

    @Override public AspectRatioLayout lock(int lock) {
        if ((lock != AspectRatioLayout.HEIGHT) && (lock != AspectRatioLayout.WIDTH)) {
            throw new IllegalArgumentException("Invalid lock measurement");
        }
        if (this.lock != lock) {
            this.lock = lock;
            requestLayout();
        }
        return this;
    }

    @Override public float ratio() {
        return ratio;
    }

    @Override public int lock() {
        return lock;
    }
}
