package com.nelsonalmendra.gists.data

import android.arch.lifecycle.LiveData
import com.nelsonalmendra.gists.data.database.Gist
import com.nelsonalmendra.gists.data.database.GistDao
import com.nelsonalmendra.gists.data.network.GistsWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class GistRepository(private val gistDao: GistDao, private val gistsWebService: GistsWebService, private val executor: Executor) {

    val allGists: LiveData<List<Gist>>
    get() {
        fetchGistsList()
        return gistDao.getAllGists()
    }

    fun fetchGistsList(){
        executor.execute {
            gistsWebService.getGists().enqueue(object : Callback<List<Gist>>{

                override fun onResponse(call: Call<List<Gist>>?, response: Response<List<Gist>>?) {
                    val gists: List<Gist>? = response?.body()
                    if(gists != null)
                        executor.execute { gistDao.insertAll(gists) }
                }

                override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {}
            })
        }
    }
}