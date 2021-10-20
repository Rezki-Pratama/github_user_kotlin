package com.dicoding.aplikasi_github_user_2.ui.detail
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.databinding.ActivityDetailBinding
import com.dicoding.aplikasi_github_user_2.ui.bindingBase.BindingBaseActivity
import com.dicoding.aplikasi_github_user_2.ui.main.MainAdapter
import com.dicoding.aplikasi_github_user_2.utils.Constants
import com.dicoding.aplikasi_github_user_2.utils.Utils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BindingBaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var detailPage : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailPage = findViewById(R.id.detail_page)
        intent.getStringExtra(EXTRA_NAME)?.let { getDetailUser(it) }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            println(position)
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

            val fragFollower = FollowerFragment()
            val fragFollowing = FollowingFragment()
            val args = Bundle()
            val args2 = Bundle()

            args.putString(FollowerFragment.EXTRA_NAME, "")
            fragFollower.arguments = args

            args2.putString(FollowingFragment.EXTRA_NAME, "")
            fragFollowing.arguments = args2

    }

    private fun getDetailUser(name: String) {
        hideLoading()
        println(name)
        viewModel.getDetail(applicationContext,name).observe(this, {
            it?.let { apiResult ->

                Log.e("LOG DETAIL", apiResult.toString())
                when (apiResult.status) {
                    Constants.API_STATUS.SUCCESS -> {
                        detailPage.visibility = View.VISIBLE
                        hideLoading()
                        apiResult.data?.let { resultData ->
                            binding.tvUserName.text = resultData.login
                            binding.tvType.text = resultData.type
                            binding.tvFollower.text = resultData.followers
                            binding.tvFollowing.text = resultData.following
                            Glide.with(applicationContext)
                                .load(resultData.avatarUrl)
                                .circleCrop()
                                .into(binding.profileImage)
                        }
                    }
                    Constants.API_STATUS.ERROR -> {
                        detailPage.visibility = View.VISIBLE
                        hideLoading()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Constants.API_STATUS.LOADING -> {
                        detailPage.visibility = View.VISIBLE
                        showLoading(this)
                    }
                }
            }
        })
    }

    companion object {
        const val EXTRA_NAME = "extra_name"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}