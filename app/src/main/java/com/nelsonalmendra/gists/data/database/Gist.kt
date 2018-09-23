package com.nelsonalmendra.gists.data.database

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "gists")
data class Gist (
        @PrimaryKey
        val id: String,
        @Embedded
        val owner: Owner,
        val description: String?,
        val updated_at: String
) : Serializable

data class Owner(
        val avatar_url: String
) : Serializable