package com.dicoding.aplikasi_github_user_2.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.data.model.ThemeModeEntity
import com.dicoding.aplikasi_github_user_2.ui.bindingBase.BindingBaseActivity
import com.dicoding.aplikasi_github_user_2.ui.favorites.FavoritesActivity
import com.dicoding.aplikasi_github_user_2.utils.Constants
import com.dicoding.aplikasi_github_user_2.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BindingBaseActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter : MainAdapter
    private lateinit var searchView : SearchView
    private var searchList:MutableList<GitUser> ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                val moveIntent = Intent(this@MainActivity, FavoritesActivity::class.java)
                startActivity(moveIntent)
                false
            }
            R.id.action_setting -> {
                chooseThemeDialog()
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_theme_text))
        val styles = arrayOf("Light","Dark","System default")
        var checkedItem = 0

        viewModel.getIsMode(1).observe(this, { mode ->
            if (mode != null) {
                checkedItem = mode
            } else {
                val mode = ThemeModeEntity(
                    id=1,
                    mode=0
                )
                viewModel.insert(mode)
            }
        })

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                    val mode = ThemeModeEntity(
                        id=1,
                        mode=0
                    )
                    viewModel.update(mode)
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                    val mode = ThemeModeEntity(
                        id=1,
                        mode=1
                    )
                    viewModel.update(mode)
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    delegate.applyDayNight()
                    val mode = ThemeModeEntity(
                        id=1,
                        mode=2
                    )
                    viewModel.update(mode)
                    dialog.dismiss()
                }

            }

        }

        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("WrongConstant")
    fun init()
    {

        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.rvSearch)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        recyclerViewAdapter = MainAdapter(applicationContext,searchList, object : MainAdapter.OnItemClickListener {
            override fun onClick(item: GitUser) {

//                val intent= Intent(applicationContext,MapActivity::class.java)
                intent.putExtra(Constants.MOVE_BUNDLE_ITEM_SEARCH_RESULT, Utils.getStringBySearchResult(item))
                startActivity(intent)
            }
        })


        recyclerView.adapter = recyclerViewAdapter

        searchView.setOnClickListener { searchView.isIconified = false }



        makeSearch("a")

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                makeSearch(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun makeSearch(query:String) {
        hideLoading()
        println(query)
        viewModel.searchUser(applicationContext,query).observe(this, {
            it?.let { apiResult ->

                Log.e("LOG LIST GITHUB USER", apiResult.toString())
                when (apiResult.status) {
                    Constants.API_STATUS.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        hideLoading()
                        apiResult.data?.let { resultData -> retrieveList(resultData.items) }
                    }
                    Constants.API_STATUS.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        hideLoading()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Constants.API_STATUS.LOADING -> {
                        showLoading(this)
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(result: MutableList<GitUser>) {
        recyclerViewAdapter.apply {
            setList(result)
            notifyDataSetChanged()
        }
    }
}
