package com.openbank.marvel_app_heroes

import com.openbank.marvel_app_heroes.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getCharacters(offset: Int =0, limit: Int = 10) = retrofitService.getCharacters(Utils.timestamp,BuildConfig.PUBLIC_KEY,Utils.hash,offset,limit)
}