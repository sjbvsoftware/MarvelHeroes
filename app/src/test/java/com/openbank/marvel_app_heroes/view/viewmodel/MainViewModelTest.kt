package com.openbank.marvel_app_heroes.view.viewmodel

import com.openbank.marvel_app_heroes.BuildConfig
import com.openbank.marvel_app_heroes.Utils
import com.openbank.marvel_app_heroes.api.RetrofitService
import com.openbank.marvel_app_heroes.data.model.ResponseData
import junit.framework.TestCase
import org.junit.Test
import retrofit2.Response

class MainViewModelTest : TestCase() {
    public override fun setUp() {
        super.setUp()
    }

    @Test
    @Throws(Exception::class)
    fun testGetCharacters() {
         val call = RetrofitService.getInstance().getCharacters(
            Utils.timestamp,
            BuildConfig.PUBLIC_KEY,
            Utils.hash,0,10)

        val response: Response<ResponseData> = call.execute()
        val responseData: ResponseData? = response.body()

        assertNotNull(responseData)
    }
}