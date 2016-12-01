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
