package com.example.kumparan.db.entity

import androidx.room.TypeConverter
import com.example.kumparan.model.Address
import com.example.kumparan.model.Company
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class Converters {
    @TypeConverter
    fun fromAddress(address: Address?): String? {
        val type = object : TypeToken<Address>() {}.type
        return Gson().toJson(address, type)
    }

    @TypeConverter
    fun toAddress(address: String?): Address? {
        val type = object : TypeToken<Address>() {}.type
        return Gson().fromJson<Address>(address, type)
    }

    @TypeConverter
    fun fromListCompany(companyList: Company?): String? {
        val type = object : TypeToken<Company>() {}.type
        return Gson().toJson(companyList, type)
    }
    @TypeConverter
    fun toListCompany(companyString: String?): Company? {
        val type = object : TypeToken<Company>() {}.type
        return Gson().fromJson<Company>(companyString, type)
    }




}