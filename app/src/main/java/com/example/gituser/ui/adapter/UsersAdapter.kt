package com.example.gituser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.gituser.R
import com.example.gituser.api.model.UsersData
import com.example.gituser.ui.base.BaseViewHolderWithController
import com.example.gituser.ui.base.Controller
import com.example.gituser.ui.common.setUrlImage
import kotlinx.android.synthetic.main.item_layout.view.*

class UsersAdapter(private val controller: UsersController) :
    ListAdapter<UsersData, BaseViewHolderWithController<UsersData, UsersController>>(usersFilterDiff){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolderWithController<UsersData, UsersController> =
        UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: BaseViewHolderWithController<UsersData, UsersController>,
        position: Int
    )  = holder.onBind(getItem(position), controller)
}

interface UsersController : Controller {
    fun onItemClicked(userName: String)
}

val usersFilterDiff = object : DiffUtil.ItemCallback<UsersData>() {
    override fun areItemsTheSame(oldItem: UsersData, newItem: UsersData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UsersData, newItem: UsersData): Boolean {
        return oldItem.id == newItem.id
    }
}

class UsersViewHolder(itemView: View) :
    BaseViewHolderWithController<UsersData, UsersController>(itemView) {
    override fun onBind(data: UsersData, controller: UsersController) {

        itemView.txt_user_name.text = data.login
        itemView.txt_user_type.text = data.type

        itemView.ig_user.setUrlImage(data.avatar_url)

        itemView.setOnClickListener {
            controller.onItemClicked(data.login)
        }
    }
}