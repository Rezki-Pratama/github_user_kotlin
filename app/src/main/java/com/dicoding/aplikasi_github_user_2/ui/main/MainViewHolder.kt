package com.dicoding.aplikasi_github_user_2.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitUser

class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val txtName: TextView = itemView.findViewById(R.id.txtName)
    val txtType: TextView = itemView.findViewById(R.id.txtDescription)
    val avatar: ImageView = itemView.findViewById(R.id.imgTypeIcon)

    fun onClick(resultItem: GitUser, listener: MainAdapter.OnItemClickListener)
    {
        itemView.setOnClickListener {
            listener.onClick(resultItem)
        }
    }
}