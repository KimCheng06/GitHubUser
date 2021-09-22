package com.example.gituser.ui.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.gituser.ui.viewmodel.MineViewModel
import com.example.gituser.R
import com.example.gituser.api.utils.Status
import com.example.gituser.ui.base.BaseFragment
import com.example.gituser.ui.common.setUrlImage
import kotlinx.android.synthetic.main.mine_fragment.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.flow.collect

class MineFragment : BaseFragment() {

    private val mineViewModel: MineViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.mine_fragment

    override fun initViewStatus() {

        lifecycleScope.launch {
            mineViewModel.userData
                .collect {
                    when(it.status){
                        Status.SUCCESS -> {
                            it.data?.let { userData ->
                                ig_user.setUrlImage(userData.avatar_url)
                                txt_user_login.text = userData.login
                                txt_user_name.text = userData.name
                                txt_user_follower.text = getString(R.string.followers, userData.followers ?: "0", userData.following ?: "0")
                                txt_email.text = userData.email
                            }

                        }
                        Status.ERROR -> {
                            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {

                        }
                    }
                }
        }
    }

}