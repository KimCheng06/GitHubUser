package com.example.gituser.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gituser.api.ApiRepository
import com.example.gituser.api.model.UserData
import com.example.gituser.api.utils.NetworkHelper
import com.example.gituser.api.utils.Resource
import com.example.gituser.api.utils.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MineViewModel(
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val userData = MutableStateFlow(
        Resource(
            Status.LOADING,
            UserData(),
            ""
        )
    )

    init {
        getUser()
    }

    private fun getUser(){
        userData.value = Resource.loading(UserData())

        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()){
                apiRepository.getUser()
                    .catch {
                        userData.value = Resource.error(it.message.toString(), null)
                    }
                    .collect {
                        userData.value = Resource.success(it.data)
                    }
            } else userData.value = Resource.error("No internet connection", null)

        }

    }

}