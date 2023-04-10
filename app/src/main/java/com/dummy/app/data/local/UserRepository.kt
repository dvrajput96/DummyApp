package com.dummy.app.data.local

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun insertUserInfo(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    fun updateUserInfo(userEntity: UserEntity) {
        userDao.update(userEntity)
    }

    fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    fun getUser(position: Int): UserEntity {
        return userDao.getUser(position)
    }

}