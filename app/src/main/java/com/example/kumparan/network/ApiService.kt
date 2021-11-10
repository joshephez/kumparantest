package com.example.kumparan.network


import com.example.kumparan.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{num}")
    suspend fun getUserById(@Path("num") num: Int?): User

    @GET("posts")
    suspend fun getPosts(): ArrayList<Posts>

    @GET("posts/{num}")
    suspend fun getPostById(@Path("num") num: Int?): Posts

    @GET("albums")
    suspend fun getAlbumById(@Query("userId") userId: Int?): ArrayList<Album>

    @GET("photos")
    suspend fun getPhotoById(@Query("id") userId: Int?): ArrayList<Photos>

    @GET("comments")
    suspend fun getCommentsByPost(@Query("postId") postId : Int?): ArrayList<Comment>

//    @POST("posts")
//    suspend fun createPost(@Body post: Posts):  Call<List<Posts>>

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>
}