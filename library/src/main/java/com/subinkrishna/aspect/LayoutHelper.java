/*
 * Copyright (C) 2016 Subinkrishna Gopi
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
