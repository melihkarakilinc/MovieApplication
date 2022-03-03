package com.melihkarakilinc.moviesapplication.View

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.melihkarakilinc.moviesapplication.Status
import com.melihkarakilinc.moviesapplication.ViewModel.MainViewModel
import com.melihkarakilinc.moviesapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getMovie(28)
        lifecycleScope.launch {
            viewModel.movieState.collect {
                when (it.status) {
                    Status.LOADING -> {
                        //Show Progressbar
                    }
                    Status.SUCCESS -> {
                        Toast.makeText(
                            this@MainActivity,
                            it.data?.results.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        Log.e("MainActivity", it.message.toString())
                    }
                }
            }
        }
    }
}