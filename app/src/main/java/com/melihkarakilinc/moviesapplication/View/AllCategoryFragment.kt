package com.melihkarakilinc.moviesapplication.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.melihkarakilinc.moviesapplication.Adapter.MoviesAdapter
import com.melihkarakilinc.moviesapplication.Genre
import com.melihkarakilinc.moviesapplication.Result
import com.melihkarakilinc.moviesapplication.Status
import com.melihkarakilinc.moviesapplication.Utils.ItemListener
import com.melihkarakilinc.moviesapplication.ViewModel.MainViewModel
import com.melihkarakilinc.moviesapplication.databinding.FragmentAllCategoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllCategoryFragment constructor(genre: Genre, context: Context) : Fragment(),ItemListener {
    var genre: Genre = genre
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentAllCategoryBinding? = null
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter()
    private var movieList = ArrayList<Result>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllCategoryBinding.inflate(inflater, container, false)
        adapter.context = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getMovie(genre.id!!)
        lifecycleScope.launch {
            viewModel.movieState.collect {
                when (it.status) {
                    Status.LOADING -> {
                        //Show Progressbar
                    }
                    Status.SUCCESS -> {
                        movieList.addAll(it.data?.results!!)
                        adapter.movieList(movieList, this@AllCategoryFragment)
                    }
                    else -> {
                        Log.e("MainActivity", it.message.toString())
                    }
                }
            }
        }
    }

    override fun OnItemSelect(result: Result) {
        TODO("Not yet implemented")
    }
}