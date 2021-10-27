package com.dicoding.aplikasi_github_user_2.ui.detail
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitDetailUser
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.databinding.ActivityDetailBinding
import com.dicoding.aplikasi_github_user_2.ui.bindingBase.BindingBaseActivity
import com.dicoding.aplikasi_github_user_2.ui.favorites.FavoritesViewModel
import com.dicoding.aplikasi_github_user_2.ui.main.MainAdapter
import com.dicoding.aplikasi_github_user_2.utils.Constants
import com.dicoding.aplikasi_github_user_2.utils.Utils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : BindingBaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val favoriteViewModel: FavoritesViewModel by viewModel()
    private lateinit var detailPage : LinearLayout
    private lateinit var detailUser : GitDetailUser
    private var isFavorite : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);



        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailPage = findViewById(R.id.detail_page)

        val name = intent.getStringExtra(EXTRA_NAME)

        name?.let { getDetailUser(it) }

        val sectionsPagerAdapter = name?.let { SectionsPagerAdapter(this, it) }
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            println(position)
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorites_item, menu)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        id?.let {
            if (menu != null) {
                getIsFavorite(it,menu)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                    val user = GithubUserEntity(
                        id = detailUser.id!!,
                        login = detailUser.login,
                        name = detailUser.name,
                        followers = detailUser.followers,
                        following = detailUser.following,
                        blog = detailUser.blog,
                        type = detailUser.type,
                        avatarUrl = detailUser.avatarUrl,
                        company = detailUser.company,
                        location = detailUser.location,
                        public_gists = detailUser.public_gists,
                        public_repos = detailUser.public_repos
                    )
                        if (isFavorite != 0) {
                            favoriteViewModel.delete(user)
                        } else {
                            favoriteViewModel.insert(user)
                        }

                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    private fun getIsFavorite(id: Int, menu: Menu) {
        favoriteViewModel.getIsFavorite(id).observe(this, { isFavorites ->
            isFavorite = isFavorites
            if (isFavorites != 0) {
                menu.findItem(R.id.action_favorite)
                    .setIcon(R.drawable.ic_baseline_favorite_24)
            } else {
                menu.findItem(R.id.action_favorite)
                    .setIcon(R.drawable.ic_baseline_favorite_border_24)
            }
        })
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
                            detailUser = resultData
                            binding.tvUserName.text = resultData.login
                            binding.tvName.text = resultData.name
                            binding.tvLocation.text = resultData.location ?: "Location Empty"
                            binding.tvCompany.text = resultData.company ?: "Company Empty"
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
        const val EXTRA_ID = "extra_od"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}