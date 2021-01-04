package com.example.channelproject

import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModelItem
import com.example.channelapp.model.listProgramm.ListProgramm
import com.example.channelproject.animation.ProminentLayoutManager
import com.example.channelproject.contract.MainContract
import com.example.channelproject.model.api.ApiModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter: MainContract.presenter {

    private lateinit var mView:MainContract.view
    private lateinit var mList:List<ChannelGroupModelItem>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManagerChannel: LinearLayoutManager
    private lateinit var layoutManagerProgramm: LinearLayoutManager

    constructor(mView:MainContract.view){
        this.mView = mView
    }
    override fun onButtonWasClicked() {
        getObservableGroupModel()?.subscribeWith(getObserverGroupModel())
    }
    override fun selectedChannel(id: String) {
        getObservableChannel(id)?.subscribeWith(getObserverChannel())
    }

//    override fun attachLinearLayout(context: Context) {
//        layoutManager = ProminentLayoutManager(context)
//        layoutManagerChannel = ProminentLayoutManager(context)
//        layoutManagerProgramm = ProminentLayoutManager(context)
//
//    }


    fun getObservableGroupModel(): io.reactivex.Observable<ChannelGroupModel>? {
        var apiModule = ApiModule()
        return apiModule.getChannelGroup().getGroupModel()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getObserverGroupModel(): DisposableObserver<ChannelGroupModel?>? {
        return object : DisposableObserver<ChannelGroupModel?>() {
            override fun onNext(@NonNull listGroupModel: ChannelGroupModel) {
                Log.d("TAG", "OnNext$listGroupModel")
                mView.setAdapterGroupModel(listGroupModel)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("TAG", "Error$e")
                e.printStackTrace()
                mView.showText(e.localizedMessage)
            }

            override fun onComplete() {
                mView.showText("complet")
            }
        }
    }

    private fun getObservableChannel(id:String): io.reactivex.Observable<ListProgramm>? {
        var apiModule = ApiModule()
        return apiModule.getListChannel().getListChannel(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getObserverChannel(): DisposableObserver<ListProgramm?>? {
        return object : DisposableObserver<ListProgramm?>() {
            override fun onNext(@NonNull movieResponse: ListProgramm) {
                Log.d("TAG", "OnNext$movieResponse")
                mView.setAdapterListProgramm(movieResponse)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("TAG", "Error$e")
                e.printStackTrace()
                mView.showText(e.localizedMessage)
            }

            override fun onComplete() {
                mView.showText("complet")
            }
        }
    }
}