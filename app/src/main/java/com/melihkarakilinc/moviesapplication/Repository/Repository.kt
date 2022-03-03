package com.melihkarakilinc.moviesapplication.Repository

import com.melihkarakilinc.moviesapplication.APIState
import com.melihkarakilinc.moviesapplication.MovieModel
import com.melihkarakilinc.moviesapplication.Network.MovieAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val movieApi: MovieAPI) {
    suspend fun getMovie(with_genres: Int): Flow<APIState<MovieModel>> {
        return flow {
            val movie=movieApi.getMovie(with_genres=with_genres)
            emit(APIState.success(movie))
        }.flowOn(Dispatchers.IO)
    }
}