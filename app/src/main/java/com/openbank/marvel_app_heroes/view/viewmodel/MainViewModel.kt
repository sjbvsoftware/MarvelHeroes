package com.openbank.marvel_app_heroes.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openbank.marvel_app_heroes.MainRepository
import com.openbank.marvel_app_heroes.Utils
import com.openbank.marvel_app_heroes.data.model.Character
import com.openbank.marvel_app_heroes.data.model.ResponseData
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    val characterList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()

    fun getCharacters(offset: Int = 0, limit: Int = 10) {
        val response = repository.getCharacters(offset, limit)
        response.enqueue(object : Callback, retrofit2.Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response?.body() != null) {
                    Utils.isLoading = false
                    characterList.postValue(response?.body()?.data?.results)
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Utils.isLoading = false
                errorMessage.postValue(t.message)
            }
        })
    }
}