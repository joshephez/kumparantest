package com.example.kumparan.builder

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kumparan.R
import com.example.kumparan.adapter.RecyclerAlbumAdapter
import com.example.kumparan.adapter.RecyclerCommentAdapter

import com.example.kumparan.databinding.UserScreenBinding
import com.example.kumparan.model.Photos
import com.example.kumparan.viewmodel.DetailViewModel
import com.example.kumparan.viewmodel.HomeViewModel
import com.example.kumparan.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    var viewModel = UserViewModel()
    var userId: Int?=0
//    lateinit var recyclerAdapter : RecyclerCommentAdapter
    lateinit var binding: UserScreenBinding
    lateinit var recyclerAdapter : RecyclerAlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = intent.getIntExtra("id", 0)
//        action bar
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        
        binding= DataBindingUtil.setContentView(this, R.layout.user_screen)
        binding.userviewmodel = viewModel
        binding.setLifecycleOwner(this)


        viewModel.initUserDetail(this,userId)
        viewModel.initUserAlbum(this,userId)
        initRecyclerAlbum()
    }


//    back route
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun initRecyclerAlbum(){
        val recycler = binding.recyclerAlbum
        recycler.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)

        recyclerAdapter = RecyclerAlbumAdapter()
        recycler.adapter = recyclerAdapter
    }

}