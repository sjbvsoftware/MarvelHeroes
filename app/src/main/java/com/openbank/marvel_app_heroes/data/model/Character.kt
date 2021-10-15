package com.openbank.marvel_app_heroes.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Character:Serializable {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null
    @SerializedName("comics")
    var comics: Comics? = null
}
