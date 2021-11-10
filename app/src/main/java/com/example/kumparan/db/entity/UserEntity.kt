package com.example.kumparan.db.entity

import androidx.room.*
import com.example.kumparan.model.Address
import com.example.kumparan.model.Company

@Entity(tableName = "users")
@TypeConverters(Converters::class)
data class UserEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "userName") val userName: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "address") val address: Address?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "website") val website: String?,
    @ColumnInfo(name = "company") val company:Company?,
)

