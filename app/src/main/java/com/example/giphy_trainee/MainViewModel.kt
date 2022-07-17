package com.example.giphy_trainee

import androidx.lifecycle.ViewModel
import com.example.giphy_trainee.koin.MainRepo

class MainViewModel(
    private val repo: MainRepo
) : ViewModel() {

    fun doNetworkCall() {
        repo.doNetworkCall()
    }
}