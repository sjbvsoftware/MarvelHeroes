package com.openbank.marvel_app_heroes.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("limit") var limit: Int,
    @SerializedName("results") var results: List<Character>? = null
)