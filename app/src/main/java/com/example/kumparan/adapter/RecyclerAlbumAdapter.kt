package com.example.kumparan.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan.R
import com.example.kumparan.builder.DetailActivity
import com.example.kumparan.builder.UserActivity
import com.example.kumparan.model.Album
import com.example.kumparan.model.Posts
import com.example.kumparan.viewmodel.UserViewModel
import kotlin.coroutines.coroutineContext

class RecyclerAlbumAdapter() : RecyclerView.Adapter<RecyclerAlbumAdapter.MyViewHolder>() {
    var items = ArrayList<Album>()


    fun setUpdatedData(items :ArrayList<Album>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int,) {

        holder.bind(items.get(position))

    }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle  = view.findViewById<TextView>(R.id.tv_albumtitle)


        fun bind(data : Album, ){
            tvTitle.text = data.title


            //tvName.text=vm.getUserName(data.userId)
        }
    }

}