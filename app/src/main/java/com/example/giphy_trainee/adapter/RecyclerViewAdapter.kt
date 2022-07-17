package com.example.giphy_trainee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy_trainee.remote.DataObject

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class RecyclerViewAdapter(val context: Context, val gifs: MutableList<DataObject>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    lateinit var mListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false),
            mListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = gifs[position]
        Glide.with(context).load(data.images.ogImage.url).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }


    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.gif_view)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
