package com.example.gituser.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.gituser.R
import com.example.gituser.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment() {

    private val fragmentList: MutableList<Fragment> = mutableListOf()

    override fun getLayoutResource(): Int = R.layout.main_fragment

    override fun initViewStatus() {
        fragmentList.add(UsersFragment())
        fragmentList.add(MineFragment())

        pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                nav_view.menu.getItem(position).isChecked = true
            }
        })

        nav_view.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_user -> {
                    pager.setCurrentItem(0, true)
                }
                R.id.navigation_mine -> {
                    pager.setCurrentItem(1, true)
                }
            }
            true
        }
    }


}