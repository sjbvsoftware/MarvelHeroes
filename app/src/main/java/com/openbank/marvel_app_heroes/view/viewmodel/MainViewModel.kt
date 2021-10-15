package com.openbank.marvel_app_heroes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.openbank.marvel_app_heroes.data.model.Characters
import com.openbank.marvel_app_heroes.data.model.Character
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    val characterList = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()

    fun getCharacters(offset: Int = 0, limit: Int = 10) {
        val response = repository.getCharacters(offset, limit)
        response.enqueue(object : Callback, retrofit2.Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response?.body() != null)
                    characterList.postValue(response?.body()?.data?.results)
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}