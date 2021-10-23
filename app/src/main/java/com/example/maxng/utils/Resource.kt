package com.example.maxng.utils

data class Resource<out T> (
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun<T> success(data: T?): Resource<T> = Resource(
            Status.SUCCESS,
            data,
            "Success"
        )

        fun<T> error(data: T?): Resource<T> = Resource(
            Status.ERROR,
            data,
            "Error fetching result"
        )

        fun<T> loading(data: T?): Resource<T> = Resource(
            Status.LOADING,
            data,
            "Please wait"
        )
    }
}
