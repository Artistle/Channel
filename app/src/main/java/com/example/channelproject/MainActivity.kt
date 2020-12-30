package com.example.channelproject

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.listProgramm.ListProgramm
import com.example.channelproject.adapter.AdapterGroup
import com.example.channelproject.adapter.AdapterChannel
import com.example.channelproject.adapter.AdapterProgramm
import com.example.channelproject.contract.MainContract

class MainActivity : AppCompatActivity(),
    AdapterGroup.Listener,
    AdapterChannel.Listener,
    AdapterProgramm.Listener,
    MainContract.view {
    private var adapterGroup: AdapterGroup? = null
    private var adapterChannel: AdapterChannel? = null
    private var adapterProgram: AdapterProgramm? = null

    private var textSelectChannel:TextView? = null

    private var recyclerGroup:RecyclerView? = null
    private var recyclerChannel:RecyclerView? = null
    private var recyclerProgram:RecyclerView? = null

    private lateinit var mPresenter:MainContract.presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSelectChannel = findViewById(R.id.logo_text)
        mPresenter = MainPresenter(this)
        initRV()
    }

    private fun initRV(){
        recyclerProgram = findViewById(R.id.recycler_program)
        recyclerGroup = findViewById(R.id.recycler_group)
        recyclerChannel = findViewById(R.id.recycler_channel)
        recyclerGroup?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true)
        recyclerChannel?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true)
        recyclerProgram?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true)
        mPresenter.onButtonWasClicked()
    }
    override fun clickSelectGroup(item: List<Channel>) {
        adapterChannel = AdapterChannel(item,this)
        recyclerChannel?.adapter = adapterChannel
    }
    override fun clickSelectChannel(selectChannel: Channel) {
        this.textSelectChannel?.text = selectChannel.name
        mPresenter.selectedChannel(selectChannel.id)
    }

    override fun clickSelectProgramm(nameChannel: String) {
        Toast.makeText(this,"Выбран канал: ${nameChannel}",Toast.LENGTH_SHORT).show()
    }
    override fun showText(message: String) {
        Toast.makeText(this,"${message}",Toast.LENGTH_SHORT).show()
    }

    override fun setAdapterGroupModel(listGroup: ChannelGroupModel) {
        adapterGroup = AdapterGroup(listGroup,this)
        recyclerGroup?.adapter = adapterGroup
    }
    override fun setAdapterListProgramm(listProgramm: ListProgramm) {
        adapterProgram = AdapterProgramm(listProgramm,this)
        recyclerProgram?.adapter = adapterProgram
    }


}