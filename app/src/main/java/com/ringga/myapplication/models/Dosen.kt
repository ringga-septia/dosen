package com.ringga.myapplication.models

data class Dosen(
    val id : Int,
    val nama : String,
    val email : String,
    val nidn : Int,
    val nrp : Int,
    val jabatan : String,
    val keterangan : String,
    val image : String,
    val role : Int)