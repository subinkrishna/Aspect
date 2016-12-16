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

/**
 * @author Subinkrishna Gopi
 */
public interface AspectRatioLayout {

    class Spec {
        int width, height;
        static Spec create(int width, int height) {
            Spec spec = new Spec();
            spec.width = width;
            spec.height = height;
            return spec;
        }
    }

    int WIDTH = 0;
    int HEIGHT = 1;

    AspectRatioLayout ratio(float ratio);
    AspectRatioLayout lock(int lock);
    float ratio();
    int lock();
    Context getContext();
    int getMeasuredWidth();
    int getMeasuredHeight();
}
