package com.example.channelproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModelItem
import com.example.channelproject.R

class AdapterGroup(var listGroup:List<ChannelGroupModelItem>, val listener:Listener): RecyclerView.Adapter<AdapterGroup.ViewHolder>() {
    interface Listener{
        fun clickSelectGroup(item:List<Channel>)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var text = itemView.findViewById<TextView>(R.id.one)
        init{
            text.setOnClickListener {listener.clickSelectGroup(listGroup.get(position).channels)}
        }

        fun bind(item:ChannelGroupModelItem) = with(itemView){

            text.setText(item.name)

        }

        override fun onClick(v: View?) {

        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_programm, parent, false))
    override fun getItemCount() = listGroup.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listGroup[position])
}