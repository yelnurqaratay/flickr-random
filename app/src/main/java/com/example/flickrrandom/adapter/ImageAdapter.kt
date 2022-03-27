package com.example.flickrrandom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.flickrrandom.R
import com.example.flickrrandom.extensions.toPx

const val DEFAULT_ADAPTER_SIZE: Int = 140

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    var listSize: Int = DEFAULT_ADAPTER_SIZE
    private val requestOptions by lazy {
        RequestOptions()
            .transform(CenterInside(), RoundedCorners(7.0f.toPx.toInt()))
            .placeholder(R.drawable.img_placeholder)
            .skipMemoryCache(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return listSize
    }

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
            Glide.with(itemView.context)
                .load("http://loremflickr.com/100/100?random=$absoluteAdapterPosition")
                .apply(requestOptions)
                .into(imageView)
        }
    }
}