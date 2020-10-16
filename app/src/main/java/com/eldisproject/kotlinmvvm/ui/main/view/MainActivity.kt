package com.eldisproject.kotlinmvvm.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eldisproject.kotlinmvvm.R
import com.eldisproject.kotlinmvvm.data.api.ApiHelper
import com.eldisproject.kotlinmvvm.data.api.ApiServiceImpl
import com.eldisproject.kotlinmvvm.data.model.User
import com.eldisproject.kotlinmvvm.databinding.ActivityMainBinding
import com.eldisproject.kotlinmvvm.ui.base.ViewModelFactory
import com.eldisproject.kotlinmvvm.ui.main.adapter.MainRecyclerViewAdapter
import com.eldisproject.kotlinmvvm.ui.main.viewmodel.MainViewModel
import com.eldisproject.kotlinmvvm.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MainRecyclerViewAdapter(arrayListOf())
        recyclerview.addItemDecoration(
            DividerItemDecoration(recyclerview.context, (recyclerview.layoutManager as LinearLayoutManager).orientation)
        )

        recyclerview.adapter = adapter
    }

    //observe
    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { users ->
                        renderList(users)
                    }
                    recyclerview.visibility = View.VISIBLE
                }

                Status.LOADING -> {
//                    dataBinding.apply {
//                        progressBar.visibility = View.VISIBLE
//                        recyclerview.visibility = View.GONE
//                    }
                    progress_bar.visibility = View.VISIBLE
                    recyclerview.visibility = View.GONE
                }

                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>){
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }


}