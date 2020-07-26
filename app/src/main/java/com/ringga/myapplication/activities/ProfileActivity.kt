package com.ringga.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ringga.myapplication.R
import com.ringga.myapplication.activities.data.Absen
import com.ringga.myapplication.activities.data.PassActivity
import com.ringga.myapplication.storage.SharedPrefManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.foto


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)




            //fungsi tombol pada form
        keluar.setOnClickListener {
            SharedPrefManager.getInstance(this)!!.clear()
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
        btn_absen.setOnClickListener {
            startActivity(Intent(baseContext, Absen::class.java))
        }
        btn_pass.setOnClickListener {
            startActivity(Intent(baseContext, PassActivity::class.java))
        }



        tampilUser()
    }
private fun tampilUser(){
    val mypref = SharedPrefManager.getInstance(this)!!.user
    val nama = mypref.nama
    val email= mypref.email
    val school = mypref.nrp.toString()
    val nim = mypref.nidn.toString()
    var image = "http://192.168.43.8/api_ta/assets/gambar_profil/" + mypref.image
    tv_nama.setText("Name  :  " + nama)
    tv_email.setText("Email  : " + email)
    tv_Prodi.setText("Prodi  : " + school)
    tv_nim.setText("Nim  : " + nim)



    Picasso.get().load(image).resize(253,253).centerCrop().into(foto)

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
