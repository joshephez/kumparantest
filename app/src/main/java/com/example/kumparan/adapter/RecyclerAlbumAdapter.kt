package com.example.kumparan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan.builder.UserActivity
import com.example.kumparan.databinding.ItemAlbumBinding
import com.example.kumparan.model.Album
import com.example.kumparan.model.Photos
import com.example.kumparan.viewmodel.UserViewModel

class RecyclerAlbumAdapter(private val viewModel: UserViewModel) : RecyclerView.Adapter<RecyclerAlbumAdapter.MyViewHolder>() {
    var items = ArrayList<Album>()
    var photoItems = ArrayList<Photos>()


    lateinit var recyclerPhotoAdapter : RecyclerPhotoAdapter

    fun setUpdatedData(items: ArrayList<Album>){

        this.items = items
        notifyDataSetChanged()
    }


    fun initData(list:ArrayList<Photos>){
       this.photoItems = list
        notifyDataSetChanged()
    }
    fun setPhotoData(photoItems: ArrayList<Photos>){
        this.photoItems = photoItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding  = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val recyclerPhoto =  binding.rvPhoto
        recyclerPhoto.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL, false)
        val decoration = DividerItemDecoration(binding.root.context, DividerItemDecoration.HORIZONTAL)
        recyclerPhoto.addItemDecoration(decoration)
        recyclerPhotoAdapter = RecyclerPhotoAdapter()
        recyclerPhoto.adapter = recyclerPhotoAdapter
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

   with(holder){

       with(items.get(position)){

    binding.tvAlbumtitle.text = this.title

    viewModel.initUserPhoto(binding.root.context as UserActivity,items[position].id)

    recyclerPhotoAdapter.setUpdatedData(photoItems)



       }
   }

    }

    inner class MyViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)
}

