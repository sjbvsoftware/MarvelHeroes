package com.openbank.marvel_app_heroes.data.model

import com.google.gson.annotations.SerializedName

class Comics {
    @SerializedName("items") var items: List<Item>? = null
}