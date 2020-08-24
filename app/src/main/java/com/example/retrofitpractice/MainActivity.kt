package com.example.retrofitpractice

import ApiService
import adapter.Adapter
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import models.PlaceResponse
import network.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var placeAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        placeAdapter = Adapter(arrayListOf())
        println(placeAdapter.itemCount)
        recycler_view.adapter = placeAdapter
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
       fetchData()
    }



    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.getAllPlace()


        call.enqueue(object : Callback<PlaceResponse> {
            override fun onFailure(call: Call<PlaceResponse>, t: Throwable) {
                println( "${t.message}")
            }

            override fun onResponse(
                call: Call<PlaceResponse>,
                response: Response<PlaceResponse>
            ) {

                if (response.isSuccessful) {
                    val list=response.body()?.places;
                    if (list != null) {
                        response.body().let {
                            if (it != null) {
                                println(it.places)
                                placeAdapter.placeList=it.places;
                            }
                        }

                    }
                    placeAdapter.notifyDataSetChanged()

                }
                else{
                    Toast.makeText(this@MainActivity, "NO", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })



    }
}


