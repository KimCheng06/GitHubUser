package com.example.gituser.ui.fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gituser.R
import com.example.gituser.api.utils.Status
import com.example.gituser.ui.adapter.UsersAdapter
import com.example.gituser.ui.adapter.UsersController
import com.example.gituser.ui.base.BaseFragment
import com.example.gituser.ui.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.users_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment(), UsersController {

    private val usersViewModel : UsersViewModel by viewModel()

    private val usersAdapter = UsersAdapter(this)

    override fun getLayoutResource(): Int = R.layout.users_fragment

    override fun initViewStatus() {
        rlv_users.layoutManager = LinearLayoutManager(context)

        rlv_users.adapter = usersAdapter

        lifecycleScope.launch {
            usersViewModel.usersData
                .collect {
                    when(it.status){
                        Status.SUCCESS -> {
                            it.data?.let { users ->
                                usersAdapter.submitList(users)
                            }
                            rlv_users.visibility = View.VISIBLE
                            progress.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            progress.visibility = View.GONE
                            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            rlv_users.visibility = View.GONE
                            progress.visibility = View.VISIBLE
                        }
                    }
                }
        }
    }

    override fun onItemClicked(userName: String) {
        usersViewModel.toInfoPage(userName)
    }

}