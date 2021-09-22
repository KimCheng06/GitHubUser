package com.example.gituser.coordinator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.gituser.R
import com.example.gituser.ui.fragment.UserInfoFragment
import org.koin.ext.getFullName

class Navigator {

    lateinit var activity: FragmentActivity

    var lastAddTime: Long = 0
}

fun Navigator.toUserInfoPage(username: String) = addPage(UserInfoFragment.newInstance(username))

fun Navigator.addPage(fragment: Fragment, noMorePage: Boolean = true) {
    synchronized(activity.supportFragmentManager) {
        if ((System.currentTimeMillis() - lastAddTime) < 500 && noMorePage) {
            return
        }
        lastAddTime = System.currentTimeMillis()
        if (activity.supportFragmentManager.fragments.size > 0) {
            var needToHideFragment: Fragment? = null
            for (topFragment in activity.supportFragmentManager.fragments) {
                if (topFragment.isVisible) {
                    needToHideFragment = topFragment
                }
            }

            if (needToHideFragment == null) {
                activity.supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.anim_in,
                        R.anim.anim_out,
                        R.anim.anim_pop_in,
                        R.anim.anim_pop_out
                    )
                    .add(R.id.container, fragment, fragment::class.getFullName())
                    .addToBackStack(fragment::class.getFullName())
                    .commitAllowingStateLoss()
            } else {
                activity.supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.anim_in,
                        R.anim.anim_out,
                        R.anim.anim_pop_in,
                        R.anim.anim_pop_out
                    )
                    .add(R.id.container, fragment, fragment::class.getFullName())
                    .hide(needToHideFragment)
                    .addToBackStack(fragment::class.getFullName())
                    .commitAllowingStateLoss()
            }
        } else {
            activity.supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.anim_in,
                    R.anim.anim_out,
                    R.anim.anim_pop_in,
                    R.anim.anim_pop_out
                )
                .add(R.id.container, fragment, fragment::class.getFullName())
                .addToBackStack(fragment::class.getFullName())
                .commitAllowingStateLoss()
        }
    }
}