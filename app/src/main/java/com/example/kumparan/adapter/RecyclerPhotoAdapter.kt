package com.example.kumparan.adapter

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

class RecyclerPhotoAdapter() : RecyclerView.Adapter<RecyclerPhotoAdapter.MyViewHolder>() {
    var items = ArrayList<Photos>()


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
             //   setImageUrl(binding.imgPhoto,this.url)

            }
        }
    }
    inner class MyViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)
//    companion object {
//        @JvmStatic
//        @BindingAdapter("imageUrl")
//        fun setImageUrl(view: ImageView, urlImage: String) {
//            Glide.with(view.context)
//                .load(urlImage)
//                .into(view)
//        }
//    }

}