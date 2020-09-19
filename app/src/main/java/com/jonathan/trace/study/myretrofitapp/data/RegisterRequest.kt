package com.jonathan.trace.study.myretrofitapp.data

data class RegisterRequest(
    val userId: String,
    val userPw: String,
    val userName: String,
    val userAge: Int
)