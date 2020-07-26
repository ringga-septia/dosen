package com.ringga.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ringga.myapplication.R
import com.ringga.myapplication.models.ClassData
import kotlinx.android.synthetic.main.item_absen.view.*

class AdapterClass(val  absen : List<ClassData>) : RecyclerView.Adapter<AdapterClass.MyHolder>() {

    class  MyHolder(val  view: View ):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_absen, parent, false)
        )
    }

    override fun getItemCount() = absen.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val absen = absen[position]
        holder.view.tv_id.text = absen.id.toString()
        holder.view.tv_name.text = absen.name
        holder.view.tv_thn.text = absen.thn.toString()
        holder.view.tv_mhs.text = absen.jlmh_mhs.toString()
    }

}