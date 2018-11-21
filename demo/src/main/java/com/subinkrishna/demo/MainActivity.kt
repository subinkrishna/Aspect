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

package com.subinkrishna.demo

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Demo grid activity
 *
 * @author Subinkrishna Gopi
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridSpacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
        val screenWidth = resources.displayMetrics.widthPixels

        findViewById<RecyclerView>(R.id.list).apply {
            setHasFixedSize(true)
            adapter = GridAdapter(items(), screenWidth)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                        outRect: Rect, view: View,
                        parent: RecyclerView, state: RecyclerView.State
                ) {
                    outRect.set(gridSpacing, gridSpacing, gridSpacing, gridSpacing)
                }
            })
        }
    }

    private fun items(): List<ViewItem> {
        return listOf(
                ViewItem.Image("https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/1.jpg", 2000 / 1333f),
                ViewItem.Image("https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/2.jpg", 1000/1500f),
                ViewItem.Text("Android's new app publishing format means a smaller app for your users and a more-efficient release process for you"),
                ViewItem.Text("Android Jetpack wants to celebrate 10 years of Android with you"),
                ViewItem.Image("https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/3.jpg", 1500/538f),
                ViewItem.Text("Material Design guidelines"),
                ViewItem.Image("https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/4.jpg", 1000/1500f),
                ViewItem.Image("https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/5.jpg", 800/800f),
                ViewItem.Image("https://raw.githubusercontent.com/subinkrishna/Aspect/master/demo/images/broken.jpg", 3 / 2f),
                ViewItem.Text("Google Play")
        )
    }
}