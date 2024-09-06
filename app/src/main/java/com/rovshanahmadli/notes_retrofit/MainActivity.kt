package com.rovshanahmadli.notes_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rovshanahmadli.notes_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecycleViewAdapter.Listener {

    private lateinit var binding: ActivityMainBinding // //binding adinda bir variable yarat ve onun hansi tip oldugunu yaz
    private val BASE_URL = "https://api.restful-api.dev/"  // URL’nin base hissesini(.com a eder olan hisse) objectin “” icine yaziriq; .com nan sora olan extension hissesini interface yaziriq.
    private var productModels : ArrayList<ProductModel>? = null // gelen data'lari alacagimiz bir ArrayList
    private var recycleViewAdapter : RecycleViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //binding'i initalize edirik.
        val view = binding.root //view'ya cevirir
        setContentView(view)    //xml yerine view yazir, view'nu verir
        //setContentView(R.layout.activity_main)  //activity icinde hansi xml gosderilecyini mueyyen eliyen setr.

        // RecyclerView
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycleview1.layoutManager = layoutManager

    loadData()
    }
    //Retrofit object created.
    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ProductAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<ProductModel>> {   //hazirladigimiz request'i ascynroush sekilde gedib alsin

            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        productModels = ArrayList(it)

                        productModels?.let {
                            recycleViewAdapter = RecycleViewAdapter(productModels!!,this@MainActivity)
                            binding.recycleview1.adapter = recycleViewAdapter
                        }

                    }
                }
            }

            override fun onFailure(p0: Call<List<ProductModel>>, p1: Throwable) {
                p1.printStackTrace() //log'da ne error oldugunu gosterecek
            }

        })
    }

    override fun onItemClick(product: ProductModel) {
        Toast.makeText(this,"Clicked: ${product.id}", Toast.LENGTH_LONG).show()
    }
}