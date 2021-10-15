package com.openbank.marvel_app_heroes.api

import com.openbank.marvel_app_heroes.data.model.ResponseData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

var BASE_URL = "https://gateway.marvel.com/v1/public/"

interface RetrofitService {

    @GET("characters")
    fun getCharacters(@Query("ts") ts: String,
                      @Query("apikey") apikey: String,
                      @Query("hash") hash: String,
                      @Query("offset") offset: Int,
                      @Query("limit") limit: Int
    ): Call<ResponseData>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}