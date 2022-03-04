package com.melihkarakilinc.moviesapplication.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.melihkarakilinc.moviesapplication.Category
import com.melihkarakilinc.moviesapplication.Utils.DetailOnPageChangeListener
import com.melihkarakilinc.moviesapplication.View.AllCategoryFragment

class ViewPagerAdapter(
    private val fm: FragmentManager,
    private val slideCount: Int,
    private val context: Context,
    private val data: Category
) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return slideCount
    }

    override fun getItem(position: Int): Fragment {
        val detailOnPageChangeListener = DetailOnPageChangeListener()
        detailOnPageChangeListener.onPageSelected(position)
        return AllCategoryFragment(data.genres?.get(position)!!, context)
    }
}