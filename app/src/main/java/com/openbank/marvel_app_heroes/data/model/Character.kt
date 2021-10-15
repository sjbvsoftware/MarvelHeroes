package com.openbank.marvel_app_heroes.view.model

import com.google.gson.annotations.SerializedName

class Character {
    @SerializedName("character")
    var name: String = ""
    var description: String = ""
    var thumbnail: Thumbnail? = null
}

class Thumbnail {
    @SerializedName("thumbnail")
    var path: String = ""
    var extension: String = ""
}