package com.melihkarakilinc.moviesapplication.View

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.melihkarakilinc.moviesapplication.Adapter.ViewPagerAdapter
import com.melihkarakilinc.moviesapplication.Category
import com.melihkarakilinc.moviesapplication.Status
import com.melihkarakilinc.moviesapplication.ViewModel.MainViewModel
import com.melihkarakilinc.moviesapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter: ViewPagerAdapter? = null
    private var genreName=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getCategory()

        lifecycleScope.launch {
            viewModel.categoryState.collect {

                when (it.status) {
                    Status.LOADING -> {
                        //Show Progressbar
                        Log.e("MainActivity", "LOADING")
                    }

                    Status.SUCCESS -> {
                        it.data?.let { data -> addArray(data) }
                        adapter = ViewPagerAdapter(
                            supportFragmentManager,
                            genreName.size,
                            this@MainActivity,
                            genreName
                        )
                        binding.viewPager.adapter = adapter
                    }

                    else -> {
                        Log.e("MainActivity", it.message.toString())
                    }
                }
            }
        }
    }
    private fun addArray(category: Category){
        category.genres?.forEach{ name ->
            genreName.add(name.toString())
        }
    }
}