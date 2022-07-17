package com.example.giphy_trainee.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("gifs/trending?api_key=v29SMl7njuDPqGqd2LdBOYFkrpQWZx1K")
     fun getGifs(): Call<GifResult>
}