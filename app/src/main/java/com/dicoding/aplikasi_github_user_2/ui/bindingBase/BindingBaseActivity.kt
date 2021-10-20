package com.dicoding.aplikasi_github_user_2.ui.bindingBase

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.aplikasi_github_user_2.utils.Utils

abstract  class BindingBaseActivity  : AppCompatActivity() {

    var progressDialog: Dialog? = null

    fun showLoading(context: Context) {
        progressDialog = Utils.buildProgressDilalog(context)
        progressDialog!!.show()
    }

    fun hideLoading() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        }
    }
}