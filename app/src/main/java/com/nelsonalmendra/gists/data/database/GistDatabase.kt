package com.nelsonalmendra.gists.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Gist::class], version = 1)
public abstract class GistDatabase : RoomDatabase() {

    abstract fun gistDao(): GistDao

    companion object {

        @Volatile
        private var INSTANCE: GistDatabase? = null

        fun getDatabase(context: Context): GistDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        GistDatabase::class.java,
                        "Gist_database")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}