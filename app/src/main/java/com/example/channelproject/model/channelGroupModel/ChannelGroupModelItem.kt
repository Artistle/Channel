package com.example.channelapp.model.channelGroupModel

import com.example.channelapp.model.channelGroupModel.Channel

data class ChannelGroupModelItem(
    val channels: List<Channel>,
    val id: String,
    val name: String
)