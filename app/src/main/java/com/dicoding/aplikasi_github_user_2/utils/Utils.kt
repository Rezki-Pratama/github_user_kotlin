package com.dicoding.aplikasi_github_user_2.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitDetailUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.google.gson.Gson

object Utils {

    fun buildProgressDilalog(context: Context): Dialog  {
        val progressDialog = ProgressDialog (context,R.style.TransparentProgressDialog)
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_indicator)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun getErrorMessage(context: Context):String {
        return context.getString(R.string.app_name)
    }

    fun getDetailUserByString(objectStr: String): GitDetailUser? {
        return Gson().fromJson(objectStr,  GitDetailUser::class.java)
    }

    fun getStringBySearchResult(item: GitUser): String {
        return Gson().toJson(item)
    }

}