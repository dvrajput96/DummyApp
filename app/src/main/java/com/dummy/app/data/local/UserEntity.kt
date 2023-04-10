package com.dummy.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val firstname: String,

    val lastname: String,

    val email: String,

    val mobile: String,
)
