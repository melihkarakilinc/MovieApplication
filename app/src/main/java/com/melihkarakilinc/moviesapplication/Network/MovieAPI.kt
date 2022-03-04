package com.melihkarakilinc.moviesapplication.Network

import com.melihkarakilinc.moviesapplication.Category
import com.melihkarakilinc.moviesapplication.MovieModel
import com.melihkarakilinc.moviesapplication.Utils.ApiUrl
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET(ApiUrl.CATEGORY_URL)
    suspend fun getCategory(
        @Query("api_key") api_key: String = ApiUrl.API_KEY,
        @Query("language") page:String="en-US"
    ): Category

    @GET(ApiUrl.MOVIE_URL)
    suspend fun getMovie(
        @Query("api_key") api_key: String = ApiUrl.API_KEY,
        @Query("sort_by") sort_by:String="popularity.desc",
        @Query("include_adult") include_adult:Boolean=false,
        @Query("include_video") include_video:Boolean=false,
        @Query("page") page:Int=1,
        @Query("with_genres") with_genres:Int
    ): MovieModel
}