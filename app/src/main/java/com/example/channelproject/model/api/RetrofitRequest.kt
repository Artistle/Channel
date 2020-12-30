package com.example.channelapp.model.api

import com.example.channelapp.model.channelGroupModel.ChannelGroupModel
import com.example.channelapp.model.listProgramm.ListProgramm
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitRequest {
    @GET("tv/group/")
    fun getGroupModel(): Observable<ChannelGroupModel>

    @GET("tv/program/{id}")
    fun getListChannel(@Path("id") id:String): Observable<ListProgramm>
}