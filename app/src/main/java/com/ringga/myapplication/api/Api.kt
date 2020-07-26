package com.ringga.myapplication.api



import com.ringga.myapplication.models.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    //untuk absen nantik di ubah
@FormUrlEncoded
@POST("tambah")
fun tambah(
    @Field("email") email:String,
    @Field("name") name:String,
    @Field("pass") password:String,
    @Field("school") school:String
    ): Call<DefaulResponse>

    //login
    @POST("classdata")
    fun classdata(): Call<List<ClassData>>

    //absen
    @FormUrlEncoded
    @POST("absenmhs")
    fun absen(
        @Field("code") code: String,
        @Field("nim") nim:Int,
        @Field("id_class") id_class: Int,
        @Field("prodi") prodi:String
    ):Call<DefaulResponse>
//cek absensi user
//   @FormUrlEncoded
//   @POST("absen")
//   fun absen(
//       @Field("nim") nim: Int
//   ):Call<ResponAbsen>

    //riset password
    @FormUrlEncoded
    @POST("ganti_pass")
    fun ganti_pass(
        @Field("id") id: String,
        @Field("verifikasiPass") verifikasiPass: String,
        @Field("oldPass") oldPass: String
    ):Call<ResponPass>

    //login dosen
@FormUrlEncoded
@POST("login")
fun logindosen(
    @Field("email") email: String,
    @Field("pass") password: String
):Call<ResponDosen>


}