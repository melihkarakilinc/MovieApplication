package com.melihkarakilinc.moviesapplication

data class APIState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): APIState<T> {
            return APIState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): APIState<T> {
            return APIState(Status.ERROR, null, msg)
        }

        fun <T> loading(): APIState<T> {
            return APIState(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}