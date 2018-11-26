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

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Subinkrishna Gopi
 */
class GridAdapter(
        private val items: List<ViewItem>,
        private val screenWidth: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_UNKNOWN = -1
        private const val VIEW_IMAGE = 0
        private const val VIEW_TEXT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_IMAGE -> ImageViewHolder.create(parent)
            VIEW_TEXT -> TextViewHolder.create(parent)
            else -> throw IllegalArgumentException("Unknown view type!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bind(getItem(position), screenWidth / 2)
            is TextViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (getItem<ViewItem>(position)) {
            is ViewItem.Image -> VIEW_IMAGE
            is ViewItem.Text -> VIEW_TEXT
            else -> VIEW_UNKNOWN
        }
    }

    private inline fun <reified T> getItem(position: Int): T? {
        return (if (position in 0..itemCount) items[position] else null) as T
    }
}