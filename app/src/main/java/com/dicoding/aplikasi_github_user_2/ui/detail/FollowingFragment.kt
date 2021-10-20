package com.dicoding.aplikasi_github_user_2.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.aplikasi_github_user_2.R
import com.dicoding.aplikasi_github_user_2.data.model.GitUser
import com.dicoding.aplikasi_github_user_2.utils.Constants
import org.koin.android.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: SectionPageViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter : FollowingAdapter
    private lateinit var emptyFollowing : TextView
    private var followingList:MutableList<GitUser> ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_following, container, false)
        recyclerView = rootView.findViewById(R.id.rvFollowing)
        emptyFollowing = rootView.findViewById(R.id.txtEmptyFollowing)
        recyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL, false)
        recyclerViewAdapter = FollowingAdapter(rootView.context,followingList, object : FollowingAdapter.OnItemClickListener {
            override fun onClick(item: GitUser) {

            }
        })
        recyclerView.adapter = recyclerViewAdapter
        val name = arguments?.getString(EXTRA_NAME)
        println(name)
        getFollowing(name.toString())
        return rootView
    }

    private fun getFollowing(name:String) {
        val appContext = requireContext().applicationContext
        viewModel.getFollowing(appContext ,name).observe(viewLifecycleOwner, {
            it?.let { apiResult ->
                emptyFollowing.visibility = View.GONE
                Log.e("LOG LIST GITHUB USER", apiResult.toString())
                when (apiResult.status) {
                    Constants.API_STATUS.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        apiResult.data?.let { resultData ->
                            if (resultData.isEmpty()) {
                                emptyFollowing.visibility = View.VISIBLE
                            }
                            emptyFollowing.visibility = View.GONE
                            retrieveList(resultData as MutableList<GitUser>) }
                    }
                    Constants.API_STATUS.ERROR -> {
                        emptyFollowing.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        Toast.makeText(appContext, it.message, Toast.LENGTH_LONG).show()
                    }
                    Constants.API_STATUS.LOADING -> {
                        emptyFollowing.visibility = View.GONE
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

    companion object {
        var EXTRA_NAME = "extra_name"
    }
}