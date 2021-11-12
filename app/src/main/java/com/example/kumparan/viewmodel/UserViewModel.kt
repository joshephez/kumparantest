package com.example.kumparan.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.kumparan.builder.DetailActivity
import com.example.kumparan.builder.UserActivity
import com.example.kumparan.model.*
import com.example.kumparan.network.ApiService
import kotlinx.android.synthetic.main.detail_screen.*
import kotlinx.android.synthetic.main.user_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 class UserViewModel(): ViewModel() {


     var user : MutableLiveData<User>
    var album : MutableLiveData<ArrayList<Album>>
    var photo : MutableLiveData<ArrayList<Photos>>

    var urlImage : String = ""
    var fullnameUser: String=""
    var emailUser: String =""
    var addressUser :String=""
    var companyUser :String=""

    var albumTitle :String = ""

    init{
        user = MutableLiveData()
        album = MutableLiveData()
        photo = MutableLiveData()
    }

    fun getDetailPhoto() : MutableLiveData<ArrayList<Photos>>{
        return photo
    }

    fun getDetailUser() : MutableLiveData<User>{
        return user
    }

    fun getDetailAlbum() : MutableLiveData<ArrayList<Album>>{
        return album
    }


    fun setDetailUser(userDetail:User){
        this.fullnameUser = userDetail.name.toString()
        this.emailUser =  userDetail.email.toString()
        this.addressUser =  userDetail.address!!.street.toString()+", "+userDetail.address!!.suite.toString()+", "+userDetail.address!!.city.toString()
        this.companyUser =  userDetail.company!!.name.toString()
    }
     fun setImage(photo:Photos){
         this.urlImage = photo.url

     }

    fun getUserApi(id: Int?){

        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getUserById(id)
            user.postValue(response)

        }
    }
    fun getPhotoApi(id: Int?){

        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getPhotoById(id)
            photo.postValue(response)
        }
    }
    fun getAlbumApi(id: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getAlbumById(id)
            album.postValue(response)
        }
    }



    fun initUserDetail(activity: UserActivity, userId:Int?){
        getUserApi(userId)

        getDetailUser().observe(activity, Observer<User> {
            if (it != null) {
                setDetailUser(it)
                CoroutineScope(Dispatchers.Main).launch {
                    activity.tv_fullname.text = fullnameUser.toString()
                    activity.tv_emailuser.text = emailUser.toString()
                    activity.tv_addressuser.text = addressUser.toString()
                    activity.tv_companyuser.text = companyUser.toString()


                }
            } else {
                Toast.makeText(activity, "Error get list", Toast.LENGTH_LONG)
            }
        })
    }

    fun initUserAlbum(activity: UserActivity, userId:Int?){
        getAlbumApi(userId)
        getDetailAlbum().observe(activity, Observer<ArrayList<Album>> {
            if (it != null) {
                CoroutineScope(Dispatchers.Main).launch {
                  activity.recyclerAdapter.setUpdatedData(it)
//                    for(i in 1..it.size)
//
//                    initUserPhoto(
//                        activity,
//                        it[0].id
//                    )

                }

            } else {
                Toast.makeText(activity, "Error get list", Toast.LENGTH_LONG)
            }
        })
    }



    @SuppressLint("ShowToast")
    fun initUserPhoto(act:UserActivity , id:Int?) {
        getPhotoApi(id)
        getDetailPhoto().observe(act, Observer<ArrayList<Photos>> {
            if (it != null) {
                CoroutineScope(Dispatchers.Main).launch {
                   act.recyclerAdapter.recyclerPhotoAdapter.setUpdatedData(it)

//                    act.recyclerAdapter.initData(it)
                    Log.e("halo","halooooo2>>   "+ it.size.toString())
                }
            } else {
                Toast.makeText(act, "Error get list", Toast.LENGTH_LONG)
            }
        })


    }
}