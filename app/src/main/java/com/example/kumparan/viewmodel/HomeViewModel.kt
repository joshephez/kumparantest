package com.example.kumparan.viewmodel


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kumparan.builder.HomeActivity
import com.example.kumparan.db.DatabaseHelper
import com.example.kumparan.db.entity.UserEntity
import com.example.kumparan.model.Posts
import com.example.kumparan.model.User
import com.example.kumparan.network.ApiService

import kotlinx.coroutines.*

class HomeViewModel() : ViewModel() {

     var posts : MutableLiveData<ArrayList<Posts>>
    var user : MutableLiveData<List<User>>
    var username :String = ""

    init{
        posts = MutableLiveData()
        user = MutableLiveData()
    }

    fun getPostsObserver() : MutableLiveData<ArrayList<Posts>>{
        return posts
    }
    fun getDetailUser() : MutableLiveData<List<User>>{
        return user
    }

    fun getUserName(id:Int):String{
        viewModelScope.launch(Dispatchers.IO) {
        // username = dbHelper.getNameofUserbyId(id)

        }
        return username
    }


    fun makeApiCall(){

        viewModelScope.launch(Dispatchers.Main) {

            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getPosts()


            posts.postValue(response)
        }
    }
    fun fetchUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getUsers()
            user.postValue(response)
            val usersToInsertInDB = mutableListOf<UserEntity>()

            for (userData in response) {
                val userList = UserEntity(
                    userData.id,
                    userData.name,
                    userData.userName,
                            userData.email,
                            userData.address,
                            userData.phone,
                            userData.website,
                            userData.company,
                )
                usersToInsertInDB.add(userList)
            }
           // dbHelper.insertAll(usersToInsertInDB)
        }
    }


}