package com.dicoding.aplikasi_github_user_2.ui.bindingBase

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.ui.main.MainAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, searchAdapter: MainAdapter) {
    view.adapter = searchAdapter
}

@BindingAdapter("adapterSearch")
fun bindAdapterPosterList(view: RecyclerView, searchResult: MutableList<GitUser>)
{
    (view.adapter as? MainAdapter)?.setList(searchResult)
}