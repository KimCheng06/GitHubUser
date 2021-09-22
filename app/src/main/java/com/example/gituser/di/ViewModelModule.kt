package com.example.gituser.di


import com.example.gituser.coordinator.Coordinator
import com.example.gituser.ui.viewmodel.MineViewModel
import com.example.gituser.ui.viewmodel.UserInfoViewModel
import com.example.gituser.ui.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { UsersViewModel(
        get<Coordinator>()::toUserInfoPage,
        get(),
        get()
    ) }

    viewModel{ (userName: String) -> UserInfoViewModel(
        userName,
        get(),
        get()
    ) }

    viewModel { MineViewModel(get(), get()) }
}