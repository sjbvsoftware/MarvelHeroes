package com.openbank.marvel_app_heroes.data.model

import com.google.gson.annotations.SerializedName

class Thumbnail (
    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null
)