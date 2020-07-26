package com.ringga.myapplication.activities.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.ringga.myapplication.R
import com.ringga.myapplication.activities.LoginActivity
import com.ringga.myapplication.api.RetrofitClient
import com.ringga.myapplication.models.ResponPass
import com.ringga.myapplication.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_pass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)
        val edit = findViewById<ProgressBar>(R.id.pb_edit)

        btn_edit.setOnClickListener {
            edit.setVisibility(View.VISIBLE)
            val mypref = SharedPrefManager.getInstance(this)!!.user
            val id = mypref.id.toString()
            val newPass = et_newPass.text.toString().trim()
            val verifikasiPass = et_verivikasi.text.toString().trim()
            val oldPass = et_pass.text.toString().trim()

            if(newPass.isEmpty()){
                et_newPass.error = "Password kosong"
                edit.setVisibility(View.GONE)
                et_newPass.requestFocus()
                return@setOnClickListener
            }
            if(verifikasiPass.isEmpty()){
                et_verivikasi.error = "Password kosong"
                edit.setVisibility(View.GONE)
                et_verivikasi.requestFocus()
                return@setOnClickListener
            }
            if(oldPass.isEmpty()){
                et_pass.error = "Password kosong"
                edit.setVisibility(View.GONE)
                et_pass.requestFocus()
                return@setOnClickListener
            }
            if (newPass == verifikasiPass){

                RetrofitClient.instance.ganti_pass( id, verifikasiPass, oldPass )
                    .enqueue(object : Callback<ResponPass>{
                        override fun onFailure(call: Call<ResponPass>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            edit.setVisibility(View.GONE)
                        }

                        override fun onResponse(
                            call: Call<ResponPass>,
                            response: Response<ResponPass>
                        ) {
                            if (response.isSuccessful){
                                edit.setVisibility(View.GONE)

                                SharedPrefManager.getInstance(this@PassActivity)!!.clear()

                                startActivity(Intent(baseContext, LoginActivity::class.java))
                                Toast.makeText(
                                    applicationContext,
                                    response.body()?.message,
                                    Toast.LENGTH_LONG
                                ).show()
                                finish()
                            }else{
                                if (response.code() == 400) {
                                    edit.setVisibility(View.GONE)
                                    Toast.makeText(
                                        applicationContext,
                                        "Old Password User Salah",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }else {
                                    edit.setVisibility(View.GONE)
                                    Toast.makeText(
                                        applicationContext,
                                        "password baru dan lama sama",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }

                    })
            }else{
                edit.setVisibility(View.GONE)
                Toast.makeText(applicationContext, "new password dan Verifikasi Password \n berbeda", Toast.LENGTH_LONG).show()
            }

        }
    }
}