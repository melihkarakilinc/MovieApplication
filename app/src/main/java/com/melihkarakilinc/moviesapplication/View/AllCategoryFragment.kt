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
import androidx.navigation.fragment.findNavController
import com.melihkarakilinc.moviesapplication.Status
import com.melihkarakilinc.moviesapplication.ViewModel.MainViewModel
import com.melihkarakilinc.moviesapplication.databinding.FragmentAllCategoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllCategoryFragment constructor(text: String, context: Context) : Fragment() {
    var text: String = text
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentAllCategoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.text.text = text
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //viewModel.getMovie(28)
        lifecycleScope.launch {
            viewModel.movieState.collect {
                when (it.status) {
                    Status.LOADING -> {
                        //Show Progressbar
                    }
                    Status.SUCCESS -> {
//                        Toast.makeText(
//                            requireContext(),
//                            it.data?.results.toString(),
//                            Toast.LENGTH_LONG
//                        ).show()
                    }
                    else -> {
                        Log.e("MainActivity", it.message.toString())
                    }
                }
            }
        }
    }
}