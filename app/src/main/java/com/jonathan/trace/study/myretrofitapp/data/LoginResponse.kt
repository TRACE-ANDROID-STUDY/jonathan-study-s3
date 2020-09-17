package com.jonathan.trace.study.myretrofitapp.data

data class LoginResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: String
)