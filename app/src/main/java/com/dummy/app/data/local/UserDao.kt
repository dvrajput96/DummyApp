package com.dummy.app.data.local

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Update(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun update(userEntity: UserEntity)

    @Query("select * from user_info")
    fun getAllUsers(): List<UserEntity>

    @Query("select * from user_info where id == :value")
    fun getUser(value: Int): UserEntity

}