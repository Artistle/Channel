package com.example.channelproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelproject.R

class AdapterChannel(var listChannel:List<Channel>, val listener: Listener): RecyclerView.Adapter<AdapterChannel.ViewHolder>() {
    interface Listener{
        fun clickSelectChannel(selectChannel:Channel)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var text = itemView.findViewById<TextView>(R.id.text1)
        var logo = itemView.findViewById<ImageView>(R.id.logo_image)
        init{
            itemView.setOnClickListener {
                listener.clickSelectChannel(listChannel.get(position))
            }
        }
        fun bind(item:Channel) = with(itemView){
            Glide
                .with(itemView.context)
                .load("http://android.tv.planeta.tc:443"+"${item.logoUrl}")
                .into(logo)

            text.setOnClickListener {
                //listener.onItemClickItemChannel(listGroup.get(position).name)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false))
    override fun getItemCount() = listChannel.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listChannel[position])

}