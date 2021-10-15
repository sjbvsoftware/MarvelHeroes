package com.openbank.marvel_app_heroes.data.model

import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("status") var status: String,
    @SerializedName("copyright") var copyright: String,
    @SerializedName("attributionText") var attributionText: String,
    @SerializedName("data") var data: Data? = null
)




