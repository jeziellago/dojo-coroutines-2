package com.dojo.coroutines.data.cache

import java.io.Serializable

typealias Cacheable = Serializable

interface Cache {

    suspend fun <T: Serializable> get(key: String, result: Result<T>.() -> Unit)

    suspend fun <T: Serializable> put(key: String, value: T, result: Result<Unit>.() -> Unit)

    suspend fun clear(key: String)

    suspend fun clear()

}