package com.e.poptest.interfaces

import com.e.poptest.DataModel.Model
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("repos")
    fun getData(): Call<List<Model>>

}