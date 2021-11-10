package com.example.kumparan.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.kumparan.db.entity.UserEntity
import com.example.kumparan.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): MutableList<UserEntity>

    @Query("SELECT name FROM users WHERE id=:id")
    fun getNameofUserbyId(id: Int): String

    @Insert
    fun insertAll(users:MutableList<UserEntity> )

    @Delete
    fun delete(user: MutableList<UserEntity>)

}