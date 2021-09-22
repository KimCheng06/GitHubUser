package com.example.gituser.api

import com.example.gituser.api.model.OneUserData
import com.example.gituser.api.model.UserData
import com.example.gituser.api.model.UsersData
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(headers : Map<String, String>): List<UsersData>

    suspend fun getUser(headers : Map<String, String>): UserData

    suspend fun getOneUser(headers : Map<String, String>, userName : String): OneUserData

}