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

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.subinkrishna.aspect.AspectRatioImageView
import com.subinkrishna.aspect.AspectRatioLayout
import com.subinkrishna.aspect.AspectRatioTextView

/**
 * Simple ViewHolders used in the demo app
 *
 * @author Subinkrishna Gopi
 */

/** Image item view holder */
class ImageViewHolder(private val v: AspectRatioImageView) : RecyclerView.ViewHolder(v) {
    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.view_image_item, parent, false)
            return ImageViewHolder(v as AspectRatioImageView)
        }
    }

    fun bind(item: ViewItem.Image?, width: Int) {
        val ratio = item?.ratio ?: 1f
        val url = item?.url.orEmpty()
        v.ratio(ratio).lock(AspectRatioLayout.WIDTH)
        Picasso.get().load(url)
                .placeholder(ColorDrawable(0xFFCCCCCC.toInt()))
                .error(R.drawable.ic_broken_image_black_24dp)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .resize(width, (width / ratio).toInt())
                .into(v)
    }
}

/** Text view holder */
class TextViewHolder(private val v: AspectRatioTextView) : RecyclerView.ViewHolder(v) {
    companion object {
        fun create(parent: ViewGroup): TextViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(
                    R.layout.view_text_item, parent, false)
            return TextViewHolder(v as AspectRatioTextView)
        }
    }

    fun bind(item: ViewItem.Text?) {
        v.text = item?.text.orEmpty()
    }
}