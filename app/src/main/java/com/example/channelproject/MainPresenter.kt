package com.example.channelproject

import android.util.Log
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.channelGroupModel.ChannelGroupModelItem
import com.example.channelapp.model.listProgramm.ListProgramm
import com.example.channelproject.contract.MainContract
import com.example.channelproject.model.api.ApiModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter: MainContract.Presenter {

    private lateinit var mMainView:MainContract.MainView
    private lateinit var mList:List<ChannelGroupModelItem>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManagerChannel: LinearLayoutManager
    private lateinit var layoutManagerProgramm: LinearLayoutManager

    constructor(mMainView:MainContract.MainView){
        this.mMainView = mMainView
    }
    override fun startApp() {
        getObservableGroupModel()?.subscribeWith(getObserverGroupModel())
    }
    override fun selectedChannel(id: String) {
        getObservableChannel(id)?.subscribeWith(getObserverChannel())
    }

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
                mMainView.setAdapterGroupModel(listGroupModel)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("TAG", "Error$e")
                e.printStackTrace()
                mMainView.showText(e.localizedMessage)
            }

            override fun onComplete() {
                mMainView.showText("complet")
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
                mMainView.setAdapterListProgramm(movieResponse)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("TAG", "Error$e")
                e.printStackTrace()
                mMainView.showText(e.localizedMessage)
            }

            override fun onComplete() {
                mMainView.showText("complet")
            }
        }
    }
}