package com.melihkarakilinc.moviesapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melihkarakilinc.moviesapplication.APIState
import com.melihkarakilinc.moviesapplication.Category
import com.melihkarakilinc.moviesapplication.MovieModel
import com.melihkarakilinc.moviesapplication.Repository.Repository
import com.melihkarakilinc.moviesapplication.Status
import com.melihkarakilinc.moviesapplication.Utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository(
        AppConfig.ApiService()
    )

    val movieState = MutableStateFlow(
        APIState(
            Status.LOADING,
            MovieModel(), ""
        )
    )

    val categoryState = MutableStateFlow(
        APIState(
            Status.LOADING,
            Category(), ""
        )
    )

    fun getMovie(with_genres: Int) {
        movieState.value = APIState.loading()
        viewModelScope.launch {
            repository.getMovie(with_genres)
                .catch {
                    movieState.value =
                        APIState.error(it.message.toString())
                }.collect { movieState.value = APIState.success(it.data) }
        }
    }

    fun getCategory() {
        categoryState.value = APIState.loading()
        viewModelScope.launch {
            repository.getCategory()
                .catch {
                    categoryState.value =
                        APIState.error(it.message.toString())
                }.collect { categoryState.value = APIState.success(it.data) }
        }
    }
}