package com.example.kumparan.db

import com.example.kumparan.db.entity.UserEntity
import com.example.kumparan.model.User


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): MutableList<UserEntity> = appDatabase.userDao().getAll()
    override suspend  fun getNameofUserbyId(id:Int): String =appDatabase.userDao().getNameofUserbyId(id)
    override suspend  fun insertAll(usersAll: MutableList<UserEntity>) = appDatabase.userDao().insertAll(usersAll)

}