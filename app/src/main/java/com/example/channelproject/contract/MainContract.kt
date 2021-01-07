package com.example.channelproject.contract

import android.content.Context
import android.view.View
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModelItem
import com.example.channelapp.model.listProgramm.ListProgramm
import com.example.channelapp.model.listProgramm.ListProgrammItem

interface MainContract {
    interface Presenter{
        fun startApp()
        fun selectedChannel(id:String)
    }
    interface MainView{
        fun showText(message:String)
        fun setAdapterGroupModel(listGroup:ChannelGroupModel)
        fun setAdapterListProgramm(listProgramm:ListProgramm)
    }
}