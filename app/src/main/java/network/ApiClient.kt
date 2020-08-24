package network

import ApiService
import com.google.gson.GsonBuilder
import network.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    val getClient: ApiService
        get() {
            val gson = GsonBuilder().setLenient().create()
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder().baseUrl(Constant.baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
            return retrofit.create(ApiService::class.java)
        }
}