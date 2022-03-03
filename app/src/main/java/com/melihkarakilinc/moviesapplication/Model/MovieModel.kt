package com.melihkarakilinc.moviesapplication

data class MovieModel(
    val page: Int?=null,
    val results: List<Result>?=null,
    val total_pages: Int?=null,
    val total_results: Int?=null
)

data class Result(
    val adult: Boolean?=null,
    val backdrop_path: String?=null,
    val genre_ids: List<Int>?=null,
    val id: Int?=null,
    val original_language: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    var poster_path: String?=null,
    val release_date: String?=null,
    var title: String?=null,
    val video: Boolean?=null,
    val vote_average: Double?=null,
    val vote_count: Int?=null
)