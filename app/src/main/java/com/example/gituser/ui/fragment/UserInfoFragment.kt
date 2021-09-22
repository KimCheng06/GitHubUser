package com.example.gituser.ui.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope
import com.example.gituser.R
import com.example.gituser.api.utils.Status
import com.example.gituser.ui.base.BaseFragment
import com.example.gituser.ui.common.setUrlImage
import com.example.gituser.ui.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.user_info_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserInfoFragment : BaseFragment() {

    companion object {

        private const val KEY_USERNAME = "key_username"

        fun newInstance(userName: String) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_USERNAME, userName)
            }
        }
    }

    private val userInfoViewModel: UserInfoViewModel by viewModel {
        parametersOf(arguments?.getString(KEY_USERNAME) ?: "")
    }

    override fun getLayoutResource(): Int = R.layout.user_info_fragment

    override fun initViewStatus() {

        context?.let {
            val closeColor = ContextCompat.getColor(it, R.color.black)
            val closeIcon = ContextCompat.getDrawable(it, R.drawable.ic_close)
            DrawableCompat.setTint(closeIcon!!, closeColor)
            toolbar.navigationIcon = closeIcon
        }

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        lifecycleScope.launch {
            userInfoViewModel.oneUserData
                .collect {
                    when(it.status){
                        Status.SUCCESS -> {
                            it.data?.let { userInfo ->
                                ig_user.setUrlImage(userInfo.avatar_url)
                                txt_user_name.text = userInfo.name
                                txt_user_login.text = userInfo.login
                                txt_location.text = userInfo.location
                                txt_blog.text = userInfo.blog
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
        }
    }
}