package com.example.kumparan.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kumparan.db.dao.UserDao
import com.example.kumparan.db.entity.Converters
import com.example.kumparan.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 2,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}