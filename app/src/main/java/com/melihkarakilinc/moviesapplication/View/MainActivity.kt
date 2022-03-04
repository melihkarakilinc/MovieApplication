package com.melihkarakilinc.moviesapplication.View

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.melihkarakilinc.moviesapplication.Adapter.ViewPagerAdapter
import com.melihkarakilinc.moviesapplication.Category
import com.melihkarakilinc.moviesapplication.Status
import com.melihkarakilinc.moviesapplication.Utils.DetailOnPageChangeListener
import com.melihkarakilinc.moviesapplication.ViewModel.MainViewModel
import com.melihkarakilinc.moviesapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter: ViewPagerAdapter? = null
    private val detailOnPageChangeListener = DetailOnPageChangeListener()
    private var category = Category()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getCategory()

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.actionbar.txtGenre.text = category.genres!![position].name
            }
        })
        detailOnPageChangeListener.getCurrentPage()

        lifecycleScope.launch {
            viewModel.categoryState.collect {

                when (it.status) {
                    Status.LOADING -> {
                        Log.e("MainActivity", "LOADING")
                    }
                    Status.SUCCESS -> {
                        binding.actionbar.txtGenre.text = it.data?.genres?.get(0)?.name ?: "Category"
                        category = it.data!!
                        adapter = ViewPagerAdapter(
                            supportFragmentManager,
                            it.data.genres!!.size,
                            this@MainActivity,
                            it.data
                        )
                        binding.viewPager.adapter = adapter
                    }
                    else -> {
                        binding.mainProgressbar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}