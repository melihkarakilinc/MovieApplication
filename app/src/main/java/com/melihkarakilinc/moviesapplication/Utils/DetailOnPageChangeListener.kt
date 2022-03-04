package com.melihkarakilinc.moviesapplication.Utils

import androidx.viewpager.widget.ViewPager

class DetailOnPageChangeListener : ViewPager.SimpleOnPageChangeListener() {

    private var currentPage = 0

    override fun onPageSelected(position: Int) {
        currentPage = position
    }

    fun getCurrentPage(): Int {
        return currentPage
    }
}