package com.nelsonalmendra.gists.data.database

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "gists")
@Parcelize
data class Gist (
        @PrimaryKey
        val id: String,
        @Embedded
        val owner: Owner,
        val description: String?,
        val updated_at: String
) : Parcelable

@Parcelize
data class Owner(
        val avatar_url: String
) : Parcelable