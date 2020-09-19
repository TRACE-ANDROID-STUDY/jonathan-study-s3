package com.jonathan.trace.study.myretrofitapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jonathan.trace.study.myretrofitapp.data.LoginRequest
import com.jonathan.trace.study.myretrofitapp.data.LoginResponse
import com.jonathan.trace.study.myretrofitapp.data.RegisterRequest
import com.jonathan.trace.study.myretrofitapp.data.RegisterResponse
import com.jonathan.trace.study.myretrofitapp.network.RequestInterface
import com.jonathan.trace.study.myretrofitapp.network.requestToServer
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException

class RegisterActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerSubmitButton.setOnClickListener{
            if (registerIdEdit.text.isNullOrBlank() || registerPwEdit.text.isNullOrBlank()
                || registerNameEdit.text.isNullOrBlank() || registerAgeEdit.text.isNullOrBlank() ){
                Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            }else {
                val id = registerIdEdit.text.toString()
                val pw = registerPwEdit.text.toString()
                val name = registerNameEdit.text.toString()
                val age = registerAgeEdit.text.toString().toInt()
                requestToServer.service.requestRegister(
                    RegisterRequest(id, pw, name, age)
                ).enqueue(object: Callback<RegisterResponse>{
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if(response.isSuccessful){
                            if(response.body()!!.success){
                                Toast.makeText(this@RegisterActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@RegisterActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Log.d("RegisterActivity", response.toString())
                            Toast.makeText(this@RegisterActivity, "Something went wrong: ${response.toString()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(this@RegisterActivity, "Something went wrong :(", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }
}