package com.jonathan.trace.study.myretrofitapp.network

import com.jonathan.trace.study.myretrofitapp.data.LoginRequest
import com.jonathan.trace.study.myretrofitapp.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST

interface RequestInterface {
    @POST("/login.php")
    fun requestLogin(@Body body: LoginRequest): Call<LoginResponse>
}