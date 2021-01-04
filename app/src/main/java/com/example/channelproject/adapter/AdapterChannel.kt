package com.example.channelproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelproject.R

class AdapterChannel(var listChannel:List<Channel>, val listener: Listener): RecyclerView.Adapter<AdapterChannel.ViewHolder>() {
    interface Listener{
        fun clickSelectChannel(selectChannel:Channel)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var logo = itemView.findViewById<ImageView>(R.id.logo_image)
        init{
            itemView.setOnClickListener {
                listener.clickSelectChannel(listChannel.get(position))
                val rv = logo.parent.parent as RecyclerView
                rv.smoothScrollToCenteredPosition(position)
            }
        }
        fun bind(item:Channel) = with(itemView){
            Glide
                .with(itemView.context)
                .load("http://android.tv.planeta.tc:443"+"${item.logoUrl}")
                .into(logo)
            return@with
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false))
    override fun getItemCount() = listChannel.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listChannel[position])
}
