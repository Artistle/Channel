package com.example.channelproject.model.api

import com.example.channelapp.model.api.RetrofitRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule() {


    fun getChannelGroup() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("http://android.tv.planeta.tc:443/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitRequest::class.java)


    fun getListChannel() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("http://android.tv.planeta.tc:443/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitRequest::class.java)
}