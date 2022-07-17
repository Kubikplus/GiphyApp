package com.example.giphy_trainee

import com.example.giphy_trainee.koin.MainRepo
import com.example.giphy_trainee.koin.MainRepoImpl
import com.example.giphy_trainee.remote.ApiInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module{

    single {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    single<MainRepo> {
        MainRepoImpl(get())
    }
    viewModel{
        MainViewModel(get())

    }




}