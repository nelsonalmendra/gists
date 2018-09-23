package com.nelsonalmendra.gists.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface GistDao {

    @Query("SELECT * FROM gists")
    fun getAllGists(): LiveData<List<Gist>>

    @Insert(onConflict = REPLACE)
    fun insertAll(gists: List<Gist>)
}