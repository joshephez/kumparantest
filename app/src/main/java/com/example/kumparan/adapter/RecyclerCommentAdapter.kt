package com.example.kumparan.adapter

import android.content.Intent
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan.builder.DetailActivity
import com.example.kumparan.R
import com.example.kumparan.model.Comment
import com.example.kumparan.model.Posts

class RecyclerCommentAdapter : RecyclerView.Adapter<RecyclerCommentAdapter.MyViewHolder>() {
    var items = ArrayList<Comment>()

    fun setUpdatedData(items :ArrayList<Comment>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))

    }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName  = view.findViewById<TextView>(R.id.tv_name)
        val tvComment = view.findViewById<TextView>(R.id.tv_comment)

        fun bind(data : Comment){
            tvName.text = data.name
            tvComment.text = data.body
        }

    }

}