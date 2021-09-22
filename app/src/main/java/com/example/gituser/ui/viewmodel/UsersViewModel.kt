package com.example.gituser.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gituser.api.ApiRepository
import com.example.gituser.api.model.UsersData
import com.example.gituser.api.utils.NetworkHelper
import com.example.gituser.api.utils.Resource
import com.example.gituser.api.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UsersViewModel(
    private val toInfoPage: (String) -> Unit,
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val usersData = MutableStateFlow(
        Resource(
            Status.LOADING,
            listOf<UsersData>(),
            ""
        )
    )
    init {
        getUserList()
    }

    private fun getUserList(){
        usersData.value = Resource.loading(listOf())

        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()){
                apiRepository.getUsers()
                    .catch {
                        usersData.value = Resource.error(it.message.toString(), null)
                    }
                    .collect {
                        usersData.value = Resource.success(it.data)
                    }
            } else usersData.value = Resource.error("No internet connection", null)
        }
    }


    fun toInfoPage(userName: String) = toInfoPage.invoke(userName)
}