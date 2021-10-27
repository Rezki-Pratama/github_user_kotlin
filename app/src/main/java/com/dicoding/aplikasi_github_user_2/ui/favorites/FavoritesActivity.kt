package com.dicoding.aplikasi_github_user_2.ui.favorites

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.databinding.ActivityDetailBinding
import com.dicoding.aplikasi_github_user_2.databinding.ActivityFavoritesBinding
import com.dicoding.aplikasi_github_user_2.ui.bindingBase.BindingBaseActivity
import com.dicoding.aplikasi_github_user_2.ui.main.MainAdapter
import com.dicoding.aplikasi_github_user_2.utils.Constants
import com.dicoding.aplikasi_github_user_2.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesActivity : BindingBaseActivity() {
    private lateinit var binding: ActivityFavoritesBinding
    private val favoriteViewModel: FavoritesViewModel by viewModel()
    private lateinit var recyclerViewAdapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView
    private var userList:MutableList<GithubUserEntity> ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorites"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        init()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    @SuppressLint("WrongConstant")
    fun init() {
        recyclerView = findViewById(R.id.rv_favorites)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerViewAdapter = FavoritesAdapter(applicationContext,userList, object : FavoritesAdapter.OnItemClickListener {
            override fun onClick(item: GithubUserEntity) {
//                intent.putExtra(Constants.MOVE_BUNDLE_ITEM_SEARCH_RESULT, Utils.getStringBySearchResult(item as GitUser))
//                startActivity(intent)
            }
        })

        recyclerView.adapter = recyclerViewAdapter

        favoriteViewModel.getAllFavorites().observe(this, { userList ->
            if (userList != null) {
                retrieveList(userList as MutableList<GithubUserEntity>)
            }
        })
    }

    private fun retrieveList(result: MutableList<GithubUserEntity>) {
        recyclerViewAdapter.apply {
            setList(result)
            notifyDataSetChanged()
        }
    }
}

