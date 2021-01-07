package com.example.channelproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.channelapp.model.channelGroupModel.Channel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.listProgramm.ListProgramm
import com.example.channelproject.adapter.AdapterGroup
import com.example.channelproject.adapter.AdapterChannel
import com.example.channelproject.adapter.AdapterProgram
import com.example.channelproject.animation.LinearHorizontalSpacingDecoration
import com.example.channelproject.animation.OffsetDecoration
import com.example.channelproject.animation.ProminentLayoutManager
import com.example.channelproject.contract.MainContract
import com.example.channelproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    AdapterGroup.Listener,
    AdapterChannel.Listener,
    AdapterProgram.Listener,
    MainContract.MainView {
    private var adapterGroup: AdapterGroup? = null
    private var adapterChannel: AdapterChannel? = null
    private var adapterProgram: AdapterProgram? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManagerChannel: LinearLayoutManager
    private lateinit var layoutManagerProgramm: LinearLayoutManager
    private lateinit var mPresenter:MainContract.Presenter
    private lateinit var snapHelper: SnapHelper
    private lateinit var snapHelperChannel: SnapHelper
    private lateinit var snapHelperProgramm: SnapHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPresenter = MainPresenter(this)

        layoutManager = ProminentLayoutManager(this)
        layoutManagerChannel = ProminentLayoutManager(this)
        layoutManagerProgramm = ProminentLayoutManager(this)

        snapHelper = PagerSnapHelper()
        snapHelperChannel = PagerSnapHelper()
        snapHelperProgramm = PagerSnapHelper()


        with(binding.recyclerGroup){
            layoutManager = this@MainActivity.layoutManager
            val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
            addItemDecoration(OffsetDecoration())
        }
        snapHelper.attachToRecyclerView(binding.recyclerGroup)

        with(binding.recyclerChannel){
            layoutManager = this@MainActivity.layoutManagerChannel
            val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
            addItemDecoration(OffsetDecoration())
        }
        snapHelperChannel.attachToRecyclerView(binding.recyclerChannel)

        with(binding.recyclerProgram){
            //layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,true)
            layoutManager = this@MainActivity.layoutManagerProgramm
            val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
            addItemDecoration(OffsetDecoration())
        }
        snapHelperProgramm.attachToRecyclerView(binding.recyclerProgram)

        initRV()
    }
    private fun initRV(){
        mPresenter.startApp()
    }

    override fun clickSelectGroup(item: List<Channel>) {
        binding.recyclerChannel.adapter = AdapterChannel(item,this@MainActivity)
    }
    override fun clickSelectChannel(selectChannel: Channel) {
        binding.logoText.text = selectChannel.name
        mPresenter.selectedChannel(selectChannel.id)
    }

    override fun clickSelectProgram(nameChannel: String) {
        Toast.makeText(this@MainActivity,"Выбран канал: $nameChannel",Toast.LENGTH_SHORT).show()

    }
    override fun showText(message: String) {
    }

    override fun setAdapterGroupModel(listGroup: ChannelGroupModel) {
        adapterGroup = AdapterGroup(listGroup,this)
        binding.recyclerGroup.adapter = adapterGroup
    }
    override fun setAdapterListProgramm(listProgramm: ListProgramm) {
        adapterProgram = AdapterProgram(listProgramm,this)
        binding.recyclerProgram.adapter = adapterProgram
    }

}