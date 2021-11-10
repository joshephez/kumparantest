package com.example.kumparan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kumparan.R
import com.example.kumparan.databinding.ItemPhotoBinding
import com.example.kumparan.model.Photos

class RecyclerPhotoAdapter() : RecyclerView.Adapter<RecyclerPhotoAdapter.MyViewHolder>() {
    var items = ArrayList<Photos>()

    fun setUpdatedData(items :ArrayList<Photos>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater)
        return MyViewHolder(binding)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
//        return MyViewHolder(view)
    }



        override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(items.get(position))

//        holder.itemView.setOnClickListener{
//
//            val intent = Intent(it.context, DetailActivity::class.java)
//            intent.putExtra("id",  items[position].id)
//            it.context.startActivity(intent)
//        }
    }
    class MyViewHolder(val binding:  ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Photos, ){
            with(binding){

                tvPhoto.text = "asda"
            }


            //tvName.text=vm.getUserName(data.userId)
        }
    }

  // @BindingAdapter("imageUrl")
   /* fun setImageUrl(imgView: ImageView, imgUrl: String?){

        imgUrl?.let {
            val imgUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
        }
    }*/

}