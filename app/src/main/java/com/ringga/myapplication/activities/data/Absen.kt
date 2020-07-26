package com.ringga.myapplication.activities.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ringga.myapplication.R
import com.ringga.myapplication.adapter.AdapterClass
import com.ringga.myapplication.api.RetrofitClient
import com.ringga.myapplication.models.ClassData
import kotlinx.android.synthetic.main.activity_absen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder
import kotlin.math.log

class Absen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absen)

//        val absen = listOf(
//            ClassData(30, "pbb", 1,10),
//            ClassData(30, "pbb", 1,10),
//            ClassData(30, "pbb", 1,10),
//            ClassData(30, "pbb", 1,10),
//            ClassData(30, "pbb", 1,10),
//            ClassData(30, "pbb", 1,10),
//            ClassData(30, "pbb", 1,10)
//        )
//
//
//
//        rv_absen.layoutManager = LinearLayoutManager(this@Absen)
//        rv_absen.adapter = AdapterClass(absen)
        RetrofitClient.instance.classdata()
            .enqueue(object : Callback<List<ClassData>> {
                override fun onFailure(call: Call<List<ClassData>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<ClassData>>,
                    response: Response<List<ClassData>>
                ) {

                    val Datas:List<ClassData> =response.body()!!

//                    val stringBuilder =StringBuilder()
//
//                    for (data in Datas){
//                        stringBuilder.append("data corona di", data.id)
////                        stringBuilder.append("\n")
////                        stringBuilder.append(data.meninggal," orang meninggal")
////                        stringBuilder.append("\n")
////                        stringBuilder.append(data.positif," orang positif terjangkit")
////                        stringBuilder.append("\n")
////                        stringBuilder.append(data.sembuh," sudah sembuh")
//                    }
//                    Toast.makeText(applicationContext, stringBuilder, Toast.LENGTH_LONG).show()

                    Datas.let {
                        tampil(it)
                    }
                }

            })

    }

    private fun tampil(data: List<ClassData>) {
        rv_absen.layoutManager = LinearLayoutManager(this)
        rv_absen.adapter = AdapterClass(data)

    }
}