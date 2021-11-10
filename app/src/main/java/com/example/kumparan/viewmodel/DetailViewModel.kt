package com.example.kumparan.viewmodel

import ApiClient
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kumparan.builder.DetailActivity
import com.example.kumparan.builder.UserActivity
import com.example.kumparan.model.Comment
import com.example.kumparan.model.Posts
import com.example.kumparan.model.User
import com.example.kumparan.network.ApiService
import kotlinx.android.synthetic.main.detail_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    var content : MutableLiveData<Posts>
    var user : MutableLiveData<User>
    var comment : MutableLiveData<ArrayList<Comment>>

    var title: String=""
    var body: String=""
    var userName: String =""
    var userId:Int? = 0
    var authorName :String=""
    var commentBody :String=""


    init{
        content = MutableLiveData()
        user = MutableLiveData()
        comment = MutableLiveData()
    }

    fun getDetailContent() : MutableLiveData<Posts> {
        return content
    }

    fun getDetailComment():MutableLiveData<ArrayList<Comment>>{
        return comment
    }

    fun getDetailUser() : MutableLiveData<User> {
        return user
    }

    fun setContent(setTitle: String, setBody: String){
       this.title = setTitle
        this.body =  setBody
    }

    fun setUser(setUser: String, setUserId: Int?){
        this.userName = setUser
        this.userId = setUserId

    }

    fun setComment(setAuthorName: String, setBody: String){
        this.authorName = setAuthorName
        this.commentBody =setBody
    }

    fun getContentApi(id: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getPostById(id)
            content.postValue(response)
        }
    }

    fun getCommentApi(id: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getCommentsByPost(id)
            comment.postValue(response)
        }
    }

    fun getUserApi(id: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = ApiClient.getApiClient().create(ApiService::class.java)
            val response = retroInstance.getUserById(id)
            user.postValue(response)
        }
    }


    fun goToUserDetail(activity: DetailActivity){
        val intent = Intent(activity, UserActivity::class.java)
        intent.putExtra("id", userId)
        activity.startActivity(intent)

    }

    fun initDetailContent(activity: DetailActivity,contentId:Int?){

       getDetailContent().observe(activity, Observer<Posts> {
            if (it != null) {
                initUserData(activity,it.userId)
                setContent(it.title.toString(), it.body.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    activity.tv_title_detail.text = title.toString()
                    activity.tv_body_detail.text = body.toString()
                }
            } else {
                Toast.makeText(activity, "Error get list", Toast.LENGTH_LONG)
            }
        })
        getContentApi(contentId)
    }

    fun initUserData(activity:DetailActivity, userId: Int?){
        getUserApi(userId)
        getDetailUser().observe(activity, Observer<User> {
            if(it != null){
                setUser(it.name.toString(),it.id)
                CoroutineScope(Dispatchers.Main).launch {
                   activity.tv_username_detail.text = userName.toString()
                }
            }else{
                Toast.makeText(activity, "Error get list", Toast.LENGTH_LONG)
            }
        })
    }

    fun initCommentData(activity: DetailActivity, contentId: Int?){

        getCommentApi(contentId)
        getDetailComment().observe(activity, Observer<ArrayList<Comment>> {
            if(it != null){

                activity.recyclerAdapter.setUpdatedData(it)
            }else{
                Toast.makeText(activity, "Error get list", Toast.LENGTH_LONG)
            }
        })
    }




}