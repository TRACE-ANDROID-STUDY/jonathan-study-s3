package com.jonathan.trace.study.myretrofitapp.network

import com.jonathan.trace.study.myretrofitapp.data.LoginRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object requestToServer{
    val URL = "http://13.124.217.102"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}