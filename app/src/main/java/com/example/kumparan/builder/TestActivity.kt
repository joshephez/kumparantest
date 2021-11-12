package com.example.kumparan.builder

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.kumparan.R

class TestActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val ivImg = findViewById<ImageView>(R.id.img_test)

        Glide.with(this)
            .load("https://via.placeholder.com/600/24f355")
            //.placeholder(R.drawable.ic_launcher_background)
            .fitCenter()
            .into(ivImg)
    }
}