package com.example.channelproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModelItem
import com.example.channelproject.R

class AdapterGroup(var listGroup:List<ChannelGroupModelItem>, val listener:Listener):
        RecyclerView.Adapter<AdapterGroup.ViewHolder>() {
    interface Listener{
        fun clickSelectGroup(item:List<Channel>)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var text = itemView.findViewById<TextView>(R.id.one)
        init{
            text.setOnClickListener {
                listener.clickSelectGroup(listGroup.get(position).channels)
                val rv = text.parent.parent as RecyclerView
                rv.smoothScrollToCenteredPosition(position)
            }
        }
        fun bind(item:ChannelGroupModelItem) = with(itemView){
            text.text = item.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false))
    override fun getItemCount() = listGroup.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listGroup[position])
}