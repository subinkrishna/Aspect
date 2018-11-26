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

package com.subinkrishna.demo.ui

import android.content.Context
import android.util.AttributeSet
import com.subinkrishna.aspect.AspectRatioLayout
import com.subinkrishna.aspect.AspectRatioTextView

/**
 * A totally unnecessary extension. {@link AspectRatioTextView#ratio} is plenty sufficient.
 *
 * @author Subinkrishna Gopi
 */
class SquareTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = android.R.attr.textViewStyle
) : AspectRatioTextView(context, attrs, defStyleAttr) {

    init {
        super.ratio(1f)
    }

    override fun ratio(ratio: Float): AspectRatioLayout = this
}