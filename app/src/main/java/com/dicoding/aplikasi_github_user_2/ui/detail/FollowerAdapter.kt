package com.dicoding.aplikasi_github_user_2.ui.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitUser

class FollowerAdapter(var context: Context, private var githubResultList: MutableList<GitUser>?, private val listener: OnItemClickListener): RecyclerView.Adapter<FollowerViewHolder>() {

    fun setList(list: MutableList<GitUser>) {
        githubResultList=list
    }

    interface OnItemClickListener {
        fun onClick(item: GitUser)
    }


    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.txtName.text = githubResultList?.get(position)?.login
        holder.txtType.text = githubResultList?.get(position)?.type
        Glide.with(holder.itemView.context)
            .load(githubResultList?.get(position)?.avatarUrl)
            .circleCrop()
            .into(holder.avatar)

        holder.onClick(githubResultList!![position],listener)

        holder.itemView.setOnClickListener {

            val moveWithDataIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            moveWithDataIntent.putExtra(
                DetailActivity.EXTRA_ID, githubResultList?.get(position)!!.id
            )
            moveWithDataIntent.putExtra(
                DetailActivity.EXTRA_NAME, githubResultList?.get(position)!!.login
            )
            holder.itemView.context.startActivity(moveWithDataIntent)
        }
    }

    override fun onCreateViewHolder(parentView: ViewGroup, position: Int): FollowerViewHolder {
        val view = LayoutInflater.from(parentView.context).inflate(R.layout.card_github_user, parentView, false)
        return FollowerViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(!githubResultList.isNullOrEmpty()) {
            return githubResultList!!.size
        }
        return 0
    }




}