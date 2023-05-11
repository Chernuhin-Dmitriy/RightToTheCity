package com.example.righttothecity

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    public val photosList = ArrayList<Uri>()

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)

        fun bind(uri: Uri) {
            ivPhoto.setImageURI(uri)
        }
    }
}