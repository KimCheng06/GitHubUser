package com.example.gituser.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gituser.api.ApiRepository
import com.example.gituser.api.model.OneUserData
import com.example.gituser.api.utils.NetworkHelper
import com.example.gituser.api.utils.Resource
import com.example.gituser.api.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserInfoViewModel(
    private val userName: String,
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val oneUserData = MutableStateFlow(
        Resource(
            Status.LOADING,
            OneUserData(),
            ""
        )
    )

    init {
        getOneUser()
    }

    private fun getOneUser(){
        oneUserData.value = Resource.loading(OneUserData())

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                apiRepository.getOneUser(userName)
                    .catch {
                        oneUserData.value = Resource.error(it.message.toString(), null)
                    }
                    .collect {
                        oneUserData.value = Resource.success(it.data)
                    }
            }else oneUserData.value = Resource.error("No internet connection", null)
        }
    }

}