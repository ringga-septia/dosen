package com.ringga.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.ringga.myapplication.R
import com.ringga.myapplication.R.*
import com.ringga.myapplication.activities.data.Absen
import com.ringga.myapplication.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_profile.keluar

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_home)

       val absen = findViewById<LinearLayout>(R.id.btn_absen)
        val cek_absen = findViewById<LinearLayout>(R.id.btn_cek_absen)
        val  profile = findViewById<LinearLayout>(R.id.btn_profile)
        //fungsi tombol pada form
        keluar.setOnClickListener {
            SharedPrefManager.getInstance(this)!!.clear()
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
        absen.setOnClickListener {
            startActivity(Intent(baseContext, Absen::class.java))
        }

        profile.setOnClickListener {
            startActivity(Intent(baseContext, ProfileActivity::class.java))
        }
    }
    override fun onStart() {
        super.onStart()

        if(!SharedPrefManager.getInstance(this)!!.isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}