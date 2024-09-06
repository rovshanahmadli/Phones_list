package com.rovshanahmadli.notes_retrofit

import retrofit2.Call
import retrofit2.http.GET
//In this interface,we will write: from which endpoint, which data we will get as a List.
interface ProductAPI {

    @GET("objects")  //which works we will do, from which endpoint we will get something
    //we should notify, which method we will use. We should create a Call.

    fun getData(): Call<List<ProductModel>>
    //Bu endpoint'den datalari al(@GET vasitesi ile) ve getData cagirildiginda bir Call olunacaq ve geri donen data ProductModel olacaq ve mene List halinda gelecek.
}