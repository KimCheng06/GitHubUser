package com.example.gituser.api

import com.example.gituser.api.model.OneUserData
import com.example.gituser.api.model.UserData
import com.example.gituser.api.model.UsersData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun getUsers(@HeaderMap() headers : Map<String, String>): List<UsersData>

    @GET("/user")
    suspend fun getUser(@HeaderMap() headers : Map<String, String>): UserData

    @GET("/users/{username}")
    suspend fun getOneUser(@HeaderMap() headers : Map<String, String>,
                           @Path("username") userName : String = ""): OneUserData

}