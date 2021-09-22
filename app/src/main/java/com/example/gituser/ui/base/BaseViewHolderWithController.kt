package com.example.gituser.ui.base

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewHolderWithController<data : Any, controller : Controller>(itemView: View) :
    BaseViewHolder<data>(itemView) {
    override fun onBind(data: data) {

    }

    abstract fun onBind(data: data, controller: controller)

    open fun onBindByPayload(data: data, payload: Any?, controller: controller) {}

    open fun onAttached(data: data, controller: controller) {}

    open fun onDetached(data: data, controller: controller) {}
}

abstract class BaseViewHolder<data : Any>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    protected val disposable = CompositeDisposable()

    abstract fun onBind(data: data)

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    val context: Context = itemView.context
}

interface Controller