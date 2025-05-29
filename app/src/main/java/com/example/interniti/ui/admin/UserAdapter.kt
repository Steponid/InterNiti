package com.example.interniti.ui.admin

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interniti.R

data class UserItem(
    val Fname: String = "",
    val Lname: String = "",
    val Pname: String = "",
    val accessLevel: Long = 0,
    val Time: String = "",
    val mail: String = ""
)

class UserAdapter : ListAdapter<UserItem, UserAdapter.UserViewHolder>(DiffCallback()) {


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fioAdmin: TextView = itemView.findViewById(R.id.Fio_admin)
        private val mailAdmin: TextView = itemView.findViewById(R.id.mail_admin)
        private val accessAdmin: TextView = itemView.findViewById(R.id.access_admin)
        private val editButton: Button = itemView.findViewById(R.id.button_red_admin)

        fun bind(user: UserItem) {
            val fullName = "${user.Lname}\n${user.Fname}\n${user.Pname}"
            fioAdmin.text = fullName
            mailAdmin.text = user.mail
            accessAdmin.text = user.accessLevel.toString()

            editButton.setOnClickListener {
                val intent = Intent(itemView.context, Red_UserActivity::class.java).apply {
                    val fullName = "${user.Lname}\n${user.Fname}\n${user.Pname}"
                    putExtra("mail", user.mail)
                    putExtra("fio", fullName)
                    putExtra("access", user.accessLevel.toInt())
                    putExtra("time", user.Time)
                }
                itemView.context.startActivity(intent)



                Log.d("UserAdapter", "Кнопка нажата для: ${user.mail}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_admin, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<UserItem>() {
        override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem.mail == newItem.mail
        }

        override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem == newItem
        }
    }
}