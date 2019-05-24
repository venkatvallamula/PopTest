package com.e.poptest.CommonService

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.e.poptest.DataModel.Model
import com.e.poptest.interfaces.ApiInterface
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val liveUserResponse: MutableLiveData<List<Model>> = MutableLiveData()

    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit","create")

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://api.github.com/orgs/square/")
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadAndroidData(): MutableLiveData<List<Model>>? {

        Log.e("loadAndroidData","yes")

        val retrofitCall  = create().getAndroid()

        retrofitCall.enqueue(object : Callback<List<Model>> {
            override fun onFailure(call: Call<List<Model>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }

            override fun onResponse(call: Call<List<Model>>, response: retrofit2.Response<List<Model>>) {

                val list  = response.body()
                for (i in list.orEmpty()){
                    Log.e("on response 1:", i.full_name)
                }

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")

                Log.e("on response 2 :", liveUserResponse.toString()+" check")

            }

        })

        return liveUserResponse
    }
}
