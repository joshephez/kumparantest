package com.example.kumparan.ui


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan.builder.DetailActivity
import com.example.kumparan.R
import com.example.kumparan.db.DatabaseHelper
import com.example.kumparan.model.Posts
import com.example.kumparan.network.ApiService
import com.example.kumparan.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerPostsAdapter() : RecyclerView.Adapter<RecyclerPostsAdapter.MyViewHolder>() {
        var items = ArrayList<Posts>()

    fun setUpdatedData(items :ArrayList<Posts>){
            this.items = items
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            holder.bind(items.get(position))
            holder.itemView.setOnClickListener{

                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("id",  items[position].id)
                it.context.startActivity(intent)
            }
        }
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle  = view.findViewById<TextView>(R.id.tv_title)
        val tvBody = view.findViewById<TextView>(R.id.tv_body)
        val tvName = view.findViewById<TextView>(R.id.tv_authorname)

        fun bind(data : Posts, ){
            tvTitle.text = data.title
            tvBody.text =data.body
            //tvName.text=vm.getUserName(data.userId)
        }
    }

}