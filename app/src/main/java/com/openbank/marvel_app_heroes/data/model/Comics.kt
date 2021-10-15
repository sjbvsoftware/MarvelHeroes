package com.openbank.marvel_app_heroes.data.model

import com.google.gson.annotations.SerializedName

class Comic {
    @SerializedName("items") var items: List<Character>? = null

}