package com.example.gituser.api

import com.example.gituser.api.model.OneUserData
import com.example.gituser.api.model.UserData
import com.example.gituser.api.model.UsersData
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(headers: Map<String, String>): List<UsersData> = apiService.getUsers(headers)

    override suspend fun getUser(headers: Map<String, String>): UserData = apiService.getUser(headers)

    override suspend fun getOneUser(headers: Map<String, String>, userName: String): OneUserData = apiService.getOneUser(headers, userName)

}