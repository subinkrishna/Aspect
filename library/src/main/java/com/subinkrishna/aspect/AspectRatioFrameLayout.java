/*
 * Copyright (C) 2017 Subinkrishna Gopi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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
