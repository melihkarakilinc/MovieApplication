package com.melihkarakilinc.moviesapplication

data class Category(
    val genres: List<Genre>?=null
)

data class Genre(
    val id: Int?=null,
    val name: String?=null
)