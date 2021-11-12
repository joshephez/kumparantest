package com.example.kumparan.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kumparan.R
import com.example.kumparan.databinding.ItemPhotoBinding
import com.example.kumparan.model.Photos
import com.example.kumparan.ui.RecyclerPostsAdapter

class RecyclerPhotoAdapter(var items :ArrayList<Photos>,context :Context) : RecyclerView.Adapter<RecyclerPhotoAdapter.MyViewHolder>() {



    fun setUpdatedData(items :ArrayList<Photos>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
//        return MyViewHolder(view)
        val binding  = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

        override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(items.get(position)){
                binding.tvPhoto.text = this.title


            }
        }
    }
    inner class MyViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)


}