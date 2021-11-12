package com.example.kumparan.builder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kumparan.R
import com.example.kumparan.adapter.RecyclerAlbumAdapter
import com.example.kumparan.adapter.RecyclerPhotoAdapter

import com.example.kumparan.databinding.UserScreenBinding
import com.example.kumparan.viewmodel.UserViewModel

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
        initRecyclerAlbum(viewModel)
    }


//    back route
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun initRecyclerAlbum(vModel: UserViewModel){
        val recycler = binding.recyclerAlbum
        recycler.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)
        recyclerAdapter = RecyclerAlbumAdapter(vModel)
        recycler.adapter = recyclerAdapter


    }


}