package com.dummy.app.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dummy.app.data.local.UserEntity
import com.dummy.app.databinding.ItemUserBinding

class UserAdapter(
    private var userList: List<UserEntity>,
    private val onItemClick: (userEntity: UserEntity, position: Int) -> (Unit)
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            with(userList[position]) {
                binding.txtName.text = "$firstname  $lastname"
                binding.txtEmail.text = email
                binding.txtMobile.text = mobile

                holder.itemView.setOnClickListener {
                    onItemClick.invoke(this, position)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(userList: List<UserEntity>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

}