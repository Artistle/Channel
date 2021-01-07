package com.example.channelproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.channelapp.model.listProgramm.ListProgrammItem
import com.example.channelproject.R

class AdapterProgram(var listProgramm:List<ListProgrammItem>, val listener:Listener):
        RecyclerView.Adapter<AdapterProgram.ViewHolder>() {
    interface Listener{
        fun clickSelectProgram(nameChannel:String)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var text = itemView.findViewById<TextView>(R.id.channel_name)
        init{
            text.setOnClickListener {
                listener.clickSelectProgram(listProgramm[position].name)
                val rv = text.parent.parent as RecyclerView
                rv.smoothScrollToCenteredPosition(position)
            }
        }

        fun bind(item: ListProgrammItem) = with(itemView){
            text.text = item.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_program, parent, false))
    override fun getItemCount() = listProgramm.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listProgramm[position])
}