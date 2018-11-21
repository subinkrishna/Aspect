/*
 * Copyright (C) 2018 Subinkrishna Gopi
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

package com.subinkrishna.aspect

import android.util.AttributeSet
import androidx.core.content.res.use

/** Dimension */
data class Dimension(val width: Int, val height: Int)

/**
 * A delegate to measure the layout based on ratio & lock
 *
 * @author Subinkrishna Gopi
 * @see AspectRatioLayout
 */
class MeasureDelegate(
        val layout: AspectRatioLayout,
        val attrs: AttributeSet?
) {
    // Aspect ratio
    var ratio: Float = 0f
        set(newRatio) {
            if (field != newRatio) {
                field = newRatio
                layout.requestLayout()
            }
        }

    // Lock
    var lock: Int = AspectRatioLayout.WIDTH
        set(newLock) {
            if (field != newLock) {
                field = newLock
                layout.requestLayout()
            }
        }

    fun init() {
        attrs?.let { attrs ->
            val typedArray = layout.getContext().theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.AspectRatioLayout,
                    0,
                    0)
            typedArray?.use {
                lock = it.getInt(R.styleable.AspectRatioLayout_lock, AspectRatioLayout.WIDTH)
                ratio = it.getFloat(R.styleable.AspectRatioLayout_ratio, 0f)
            }
        }
    }

    fun measure(): Dimension {
        val w = layout.getMeasuredWidth()
        val h = layout.getMeasuredHeight()
        return when {
            (ratio <= 0) -> Dimension(w, h)
            else -> {
                val width = if (lock == AspectRatioLayout.HEIGHT) (h * ratio).toInt() else w
                val height = if (lock == AspectRatioLayout.WIDTH) (w / ratio).toInt() else h
                Dimension(width, height)
            }
        }
    }
}


