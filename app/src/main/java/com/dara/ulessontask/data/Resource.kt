package com.dara.ulessontask.data

/**
 * This class communicates the state of the network call to the UI
 */

sealed class Resource<out T> {

    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val error: String) : Resource<T>()

}

