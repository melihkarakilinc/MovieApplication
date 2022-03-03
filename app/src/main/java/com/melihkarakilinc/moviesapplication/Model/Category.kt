package com.melihkarakilinc.moviesapplication

data class Category(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)