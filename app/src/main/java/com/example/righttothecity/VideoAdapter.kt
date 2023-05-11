package com.example.righttothecity

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    public val videoList = ArrayList<Uri>()

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vvVideo: VideoView = itemView.findViewById(R.id.vvVideo)

        fun bind(uri: Uri) {
            vvVideo.setVideoURI(uri)    // Было только это
            vvVideo.setZOrderOnTop(true)
            vvVideo.start();
        }
    }
}