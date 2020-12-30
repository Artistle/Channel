package com.example.channelproject.contract

import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModelItem
import com.example.channelapp.model.listProgramm.ListProgramm
import com.example.channelapp.model.listProgramm.ListProgrammItem

interface MainContract {
    interface presenter{
        fun onButtonWasClicked()
        fun selectedChannel(id:String)
    }
    interface view{
        fun showText(message:String)
        fun setAdapterGroupModel(listGroup:ChannelGroupModel)
        fun setAdapterListProgramm(listProgramm:ListProgramm)
    }
}