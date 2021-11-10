package com.example.kumparan.builder

import android.os.Bundle
import android.util.Log
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
import com.example.kumparan.databinding.DetailScreenBinding
import com.example.kumparan.model.Comment
import com.example.kumparan.model.Posts
import com.example.kumparan.model.User
import com.example.kumparan.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//view
class DetailActivity : AppCompatActivity() {
    var contentId: Int?=0
    var viewModel = DetailViewModel()
    lateinit var recyclerAdapter :RecyclerCommentAdapter
    lateinit var binding: DetailScreenBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //get intent
            contentId = intent.getIntExtra("id", 0)
            //actionbar
            val actionbar = supportActionBar
            actionbar!!.title = "Detail Content"
            actionbar.setDisplayHomeAsUpEnabled(true)
            //binding view
            binding= DataBindingUtil.setContentView(this, R.layout.detail_screen)
            binding.detailviewmodel = viewModel
            binding.setLifecycleOwner(this)
            //on click
            binding.tvUsernameDetail.setOnClickListener(){
                viewModel.goToUserDetail(this)

            }

            //fecth viewmodel
            viewModel.initCommentData(this,contentId)
            viewModel.initDetailContent(this,contentId)

            initRecyclerAlbum()
    }

    //route back func
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    //init recycler
    private fun initRecyclerAlbum(){
        val recycler = binding.recyclerComment
        recycler.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)

        recyclerAdapter = RecyclerCommentAdapter()
        recycler.adapter = recyclerAdapter
    }
}