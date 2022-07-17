package com.example.giphy_trainee.koin

import com.example.giphy_trainee.remote.ApiInterface

class MainRepoImpl(private val api : ApiInterface): MainRepo {
    override fun doNetworkCall() {
        api.getGifs()
    }
}