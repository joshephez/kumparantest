package com.example.kumparan.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan.R
import com.example.kumparan.db.DatabaseBuilder
import com.example.kumparan.db.DatabaseHelperImpl
import com.example.kumparan.model.Posts
import com.example.kumparan.viewmodel.HomeViewModel


class PostsRecylerFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var recyclerAdapter :RecyclerPostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_recyler, container, false)
        initViewModel(view)
        initViewModel()

        return view

    }

    private fun initViewModel(view: View){

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_fragment)
        recycler.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)
        recyclerAdapter = RecyclerPostsAdapter()
        recycler.adapter = recyclerAdapter
    }

    private fun initViewModel(){

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getPostsObserver().observe(viewLifecycleOwner, Observer<ArrayList<Posts>> {

            if(it!=null){

                recyclerAdapter.setUpdatedData(it)
            }
            else{
                Toast.makeText(activity, "Error get list",Toast.LENGTH_LONG)
            }
        })
        viewModel.makeApiCall()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PostsRecylerFragment()
            }
    }
