package com.dicoding.aplikasi_github_user_2.ui.favorites

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity

class GithubUserDiffCallback(private val mOldUserList: List<GithubUserEntity>, private val mNewUserList: List<GithubUserEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUserList.size
    }
    override fun getNewListSize(): Int {
        return mOldUserList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].id == mNewUserList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserList[oldItemPosition]
        val newEmployee = mNewUserList[newItemPosition]
        return  oldEmployee.login == newEmployee.login &&
                oldEmployee.name == newEmployee.name &&
                oldEmployee.avatarUrl == newEmployee.avatarUrl &&
                oldEmployee.followers == newEmployee.followers &&
                oldEmployee.following == newEmployee.following &&
                oldEmployee.company == newEmployee.company &&
                oldEmployee.blog == newEmployee.blog &&
                oldEmployee.public_repos == newEmployee.public_repos &&
                oldEmployee.public_gists == newEmployee.public_gists &&
                oldEmployee.type == newEmployee.type &&
                oldEmployee.location == newEmployee.location
    }
}