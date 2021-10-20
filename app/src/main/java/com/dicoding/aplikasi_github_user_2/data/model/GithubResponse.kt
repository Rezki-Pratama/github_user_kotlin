package com.dicoding.aplikasi_github_user_2.data.model

import com.google.gson.annotations.SerializedName

data class GitUserResponse(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean?,
    @SerializedName("items")
    var items: MutableList<GitUser>,
    @SerializedName("total_count")
    var totalCount: Int
)