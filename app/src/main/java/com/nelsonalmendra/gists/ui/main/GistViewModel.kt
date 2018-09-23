package com.nelsonalmendra.gists.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.nelsonalmendra.gists.data.GistRepository
import com.nelsonalmendra.gists.data.database.Gist
import com.nelsonalmendra.gists.data.database.GistDatabase
import com.nelsonalmendra.gists.data.network.GistsWebService
import java.util.concurrent.Executors

public class GistViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: GistRepository

    val allGists: LiveData<List<Gist>>

    init {
        val gistDao = GistDatabase.getDatabase(application).gistDao()
        repository = GistRepository(gistDao, GistsWebService.create(), Executors.newSingleThreadExecutor())

        allGists = repository.allGists
    }

    fun refreshData() {
        repository.fetchGistsList()
    }
}