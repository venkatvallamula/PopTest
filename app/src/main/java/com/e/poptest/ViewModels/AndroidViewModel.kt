package com.e.poptest.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.e.poptest.CommonService.RetrofitService
import com.e.poptest.DataModel.Model

class AndroidViewModel: ViewModel() {

    private val mService  =  RetrofitService()

    fun getData() : MutableLiveData<List<Model>>? {
        return mService.loadData()
    }
    var isLoading = mService.progress

}
