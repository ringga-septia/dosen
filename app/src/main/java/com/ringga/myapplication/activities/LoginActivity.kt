package com.ringga.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.ringga.myapplication.R
import com.ringga.myapplication.api.RetrofitClient
import com.ringga.myapplication.models.ResponDosen
import com.ringga.myapplication.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val Login = findViewById<ProgressBar>(R.id.pb_login)

        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
                Login.setVisibility(View.VISIBLE)
            if(email.isEmpty()){
                editTextEmail.error = "Email kosong"
                Login.setVisibility(View.GONE)
                editTextEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){

                editTextPassword.error = "Password kosong"
                Login.setVisibility(View.GONE)
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.logindosen(email, password)
                .enqueue(object : Callback<ResponDosen>{
                    override fun onFailure(call: Call<ResponDosen>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message,Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<ResponDosen>,
                        response: Response<ResponDosen>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(applicationContext, response.body()?.message,Toast.LENGTH_LONG).show()
                                SharedPrefManager.getInstance(applicationContext)!!.saveUser(response.body()?.data2!!)
                                Login.setVisibility(View.GONE)
                                startActivity(Intent(baseContext, HomeActivity::class.java))
                        } else {
                            if(response.code() == 404) {
                                Login.setVisibility(View.GONE)
                                Toast.makeText(
                                    applicationContext,
                                    "password yang anda masukkan salah",
                                    Toast.LENGTH_LONG
                                ).show()
                            }else{
                                Login.setVisibility(View.GONE)
                                Toast.makeText(
                                    applicationContext,
                                    "email tidak terdaftar",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }


                })
    }
    }
    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this)!!.isLoggedIn){
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}
