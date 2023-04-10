package com.dummy.app.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dummy.app.data.local.UserEntity
import com.dummy.app.data.local.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun insertUserInfo(firstName: String, lastName: String, email: String, mobile: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUserInfo(
                UserEntity(
                    firstname = firstName,
                    lastname = lastName,
                    email = email,
                    mobile = mobile
                )
            )
        }
    }

    fun getAllUsers(): Flow<List<UserEntity>> {
        return flow {
            emit(userRepository.getAllUsers())
        }.flowOn(Dispatchers.IO)
    }

    fun getUser(position: Int): Flow<UserEntity> {
        return flow {
            emit(userRepository.getUser(position))
        }.flowOn(Dispatchers.IO)
    }

    fun editUserInfo(
        firstName: String,
        lastName: String,
        email: String,
        mobileNumber: String,
        position: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUserInfo(
                UserEntity(
                    id = position,
                    firstname = firstName,
                    lastname = lastName,
                    email = email,
                    mobile = mobileNumber
                )
            )
        }
    }

}