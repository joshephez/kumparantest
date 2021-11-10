package com.example.kumparan.db

import com.example.kumparan.db.entity.UserEntity
import com.example.kumparan.model.User

interface DatabaseHelper {
    suspend fun getUsers(): MutableList<UserEntity>
    suspend fun getNameofUserbyId(id:Int): String
    suspend fun insertAll(users: MutableList<UserEntity>)

}