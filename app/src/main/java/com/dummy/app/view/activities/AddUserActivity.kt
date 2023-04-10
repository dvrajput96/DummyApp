package com.dummy.app.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dummy.app.databinding.ActivityAddUserBinding
import com.dummy.app.view.viewmodel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding
    private val viewModel by viewModels<AddUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    override fun onStart() {
        super.onStart()

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val position = sh.getInt("position", -1)

        if (position > -1) {
            lifecycleScope.launchWhenStarted {
                viewModel.getUser(position).collect { userInfo ->
                    binding.edtFirstname.setText(userInfo.firstname)
                    binding.edtLastname.setText(userInfo.lastname)
                    binding.edtEmail.setText(userInfo.email)
                    binding.edtMobile.setText(userInfo.mobile)
                }
            }
        }

    }

    private fun initView() {
        binding.btnAddUser.setOnClickListener {

            val firstName = binding.edtFirstname.text.toString().trim()
            val lastName = binding.edtLastname.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val mobileNumber = binding.edtMobile.text.toString().trim()

            viewModel.insertUserInfo(firstName, lastName, email, mobileNumber)

        }

        binding.btnUpdateUser.setOnClickListener {

            val firstName = binding.edtFirstname.text.toString().trim()
            val lastName = binding.edtLastname.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val mobileNumber = binding.edtMobile.text.toString().trim()

            val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val position = sh.getInt("position", -1)


            viewModel.editUserInfo(
                firstName, lastName, email, mobileNumber, position
            )

            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val myEdit = sharedPreferences.edit()
            myEdit.putInt("position", -1)
            myEdit.apply()

        }

        binding.btnShowAllUser.setOnClickListener {
            val intent = Intent(this@AddUserActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putInt("position", -1)
        myEdit.apply()
    }

}