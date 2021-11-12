package com.example.kumparan.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan.R
import com.example.kumparan.builder.UserActivity
import com.example.kumparan.databinding.ItemAlbumBinding
import com.example.kumparan.model.Album
import com.example.kumparan.model.Photos
import com.example.kumparan.model.Posts
import com.example.kumparan.ui.RecyclerPostsAdapter
import com.example.kumparan.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.item_album.view.*

class RecyclerAlbumAdapter(private val viewModel: UserViewModel) :
    RecyclerView.Adapter<RecyclerAlbumAdapter.MyViewHolder>() {
    var items = ArrayList<Album>()
    var photoItems = ArrayList<Photos>()
    private val viewPool = RecyclerView.RecycledViewPool()


    lateinit var recyclerPhotoAdapter: RecyclerPhotoAdapter

    fun setUpdatedData(items: ArrayList<Album>) {

        this.items = items
        notifyDataSetChanged()
    }


    fun initData(list: ArrayList<Photos>) {
        this.photoItems = list
        notifyDataSetChanged()
    }

    fun setPhotoData(photoItems: ArrayList<Photos>) {
        this.photoItems = photoItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return MyViewHolder(view)
//        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        viewModel.initUserPhoto(holder.itemView.context as UserActivity,items.get(position).id)
        holder.bind(items.get(position))

        val photoLayoutManager = LinearLayoutManager(holder.itemView.rv_photo.context, LinearLayoutManager.HORIZONTAL, false)
        holder.itemView.rv_photo.apply {
            layoutManager = photoLayoutManager
            recyclerPhotoAdapter = RecyclerPhotoAdapter(photoItems,holder.rvPhoto.context)
            rv_photo.adapter = recyclerPhotoAdapter
            setRecycledViewPool(viewPool)
        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAlbumtitle = view.findViewById<TextView>(R.id.tv_albumtitle)
        var rvPhoto = view.findViewById<RecyclerView>(R.id.rv_photo)
        fun bind(data: Album) {
            tvAlbumtitle.text = data.title

        }
    }
}

