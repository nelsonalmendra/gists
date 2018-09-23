package com.nelsonalmendra.gists.data.network

import com.nelsonalmendra.gists.data.database.Gist
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface GistsWebService {

    @GET("gists/public")
    fun getGists() : Call<List<Gist>>

    companion object Factory {
        fun create() : GistsWebService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()

            return retrofit.create(GistsWebService::class.java)
        }
    }
}