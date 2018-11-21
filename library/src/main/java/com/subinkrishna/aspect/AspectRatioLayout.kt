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

import android.content.Context

/**
 * Interface definition for views/view groups that supports
 * aspect ratio based scaling
 *
 * @author Subinkrishna Gopi
 * @see MeasureDelegate
 */
interface AspectRatioLayout {
    companion object {
        const val WIDTH = 0
        const val HEIGHT = 1
    }

    val delegate: MeasureDelegate

    // Methods from android.view.View
    fun getContext(): Context
    fun requestLayout()
    fun getMeasuredWidth(): Int
    fun getMeasuredHeight(): Int

    // Default getters/setters for ratio and lock
    fun ratio() = delegate.ratio
    fun ratio(ratio: Float): AspectRatioLayout {
        delegate.ratio = ratio
        return this
    }

    fun lock() = delegate.lock
    fun lock(lock: Int): AspectRatioLayout {
        delegate.lock = lock
        return this
    }
}