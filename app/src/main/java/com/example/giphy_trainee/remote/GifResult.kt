package com.example.giphy_trainee.remote

import com.google.gson.annotations.SerializedName

data class GifResult(
    @SerializedName("data") val res:List<DataObject>

)
data class DataObject(
    @SerializedName("images") val images:DataGif
)

data class DataGif(
    @SerializedName("original") val ogImage: ogImage


)

data class ogImage(
    val url: String
)
