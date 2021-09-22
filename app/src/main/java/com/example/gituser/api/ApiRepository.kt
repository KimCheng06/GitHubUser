package com.example.gituser.api

import com.example.gituser.api.model.OneUserData
import com.example.gituser.api.model.UserData
import com.example.gituser.api.model.UsersData
import com.example.gituser.api.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers(): Flow<Resource<List<UsersData>>> = flow {
        val getUsers = apiHelper.getUsers(getBasicHeader())

        emit(Resource.success(getUsers))
    }.flowOn(Dispatchers.IO)

    suspend fun getUser(): Flow<Resource<UserData>> = flow {
        val getUser = apiHelper.getUser(getBasicHeader())

        emit(Resource.success(getUser))
    }.flowOn(Dispatchers.IO)

    suspend fun getOneUser(userName : String): Flow<Resource<OneUserData>> = flow {
        val getOneUser = apiHelper.getOneUser(getBasicHeader(), userName)

        emit(Resource.success(getOneUser))
    }.flowOn(Dispatchers.IO)

    private fun getBasicHeader() : Map<String, String>
    {
        val headers = HashMap<String, String>()

        headers["Authorization"] = "token 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"
        return headers
    }

}