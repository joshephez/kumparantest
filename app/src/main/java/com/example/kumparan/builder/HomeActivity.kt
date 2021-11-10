package com.example.kumparan.builder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kumparan.R
import com.example.kumparan.db.AppDatabase
import com.example.kumparan.db.DatabaseBuilder
import com.example.kumparan.db.DatabaseHelper
import com.example.kumparan.db.DatabaseHelperImpl
import com.example.kumparan.ui.PostsRecylerFragment
import com.example.kumparan.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))

//        var viewModel = HomeViewModel(dbHelper)
//        viewModel.fetchUser()

        setupFragment()
    }

    private fun setupFragment () {

        val fragment = PostsRecylerFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }
}

