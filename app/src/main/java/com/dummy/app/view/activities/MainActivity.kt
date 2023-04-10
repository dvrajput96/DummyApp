package com.dummy.app.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dummy.app.data.local.UserEntity
import com.dummy.app.databinding.ActivityMainBinding
import com.dummy.app.view.adapter.UserAdapter
import com.dummy.app.view.viewmodel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Screens: User list, Add user, edit user
 * DB
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private val viewModel by viewModels<AddUserViewModel>()
    private var userList = emptyList<UserEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllUsers().collect {
                userList = it
                userAdapter.setList(userList)
            }
        }

        userAdapter = UserAdapter(userList) { userInfo, position ->
            // Storing data into SharedPreferences
            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()
            myEdit.putInt("position", position + 1)
            myEdit.apply()
            finish()
        }
        binding.rvUsers.adapter = userAdapter
    }

}