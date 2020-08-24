package com.example.retrofitpractice

import ApiService
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import models.PlaceResponse
import network.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
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

                    val texting:TextView = findViewById<TextView>(R.id.textView)
                    //var placeRes=PlaceResponse(response);
                    texting.text=response.body()?.places?.get(1)?.name;

                }
                else{
                    Toast.makeText(this@MainActivity, "NO", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })



    }
}


