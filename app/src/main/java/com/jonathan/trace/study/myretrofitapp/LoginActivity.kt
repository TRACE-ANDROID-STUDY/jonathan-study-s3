package com.jonathan.trace.study.myretrofitapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jonathan.trace.study.myretrofitapp.data.LoginRequest
import com.jonathan.trace.study.myretrofitapp.data.LoginResponse
import com.jonathan.trace.study.myretrofitapp.network.requestToServer
import kotlinx.android.synthetic.main.activity_login_const.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_const)

        loginButton.setOnClickListener{
            if(idText.text.isNullOrBlank() || pwText.text.isNullOrBlank())
                Toast.makeText(this, "Please check ID and password.", Toast.LENGTH_SHORT).show()
            else{
                requestToServer.service.requestLogin(
                    LoginRequest(
                        idText.text.toString(),
                        pwText.text.toString()
                    )
                ).enqueue(object:Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if(response.isSuccessful){
                            if(response.body()!!.success){
                                Toast.makeText(this@LoginActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.putExtra("response", response.toString()+"\n"+response.body()!!.toString())
                                startActivity(intent)
                                finish()
                            }
                            else {
                                Toast.makeText(this@LoginActivity, "Invalid id/password.", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                        else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Failed with response code: ${response.code()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "onFailure :(", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        registerText.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}