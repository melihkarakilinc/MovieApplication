package com.melihkarakilinc.moviesapplication.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.melihkarakilinc.moviesapplication.View.AllCategoryFragment

class ViewPagerAdapter(
    fm: FragmentManager,
    slideCount: Int,
    context: Context,
    data: ArrayList<String>
) : FragmentPagerAdapter(fm) {
    val fm = fm
    val slideCount = slideCount
    val context = context
    val data = data


    override fun getCount(): Int {
        return slideCount
    }

    override fun getItem(position: Int): Fragment {
        return AllCategoryFragment(data[position], context)
    }
}