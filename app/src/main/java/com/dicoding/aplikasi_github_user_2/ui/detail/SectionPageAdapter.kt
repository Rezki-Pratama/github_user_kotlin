package com.dicoding.aplikasi_github_user_2.ui.detail

import android.app.PendingIntent.getActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.ui.bindingBase.BindingBaseActivity

class SectionsPagerAdapter(activity: AppCompatActivity, private val username: String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        val bundle = Bundle()
        bundle.putString("extra_name",username)
        fragment?.arguments = bundle
        return fragment as Fragment
    }

}