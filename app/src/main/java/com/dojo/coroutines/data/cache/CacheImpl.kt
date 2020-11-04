package com.dojo.coroutines.data.cache

import com.kachej.Kachej
import java.io.Serializable

internal class CacheImpl(
    private val kachej: Kachej
) : Cache {

    override suspend fun <T: Serializable> get(key: String, result: Result<T>.() -> Unit) {
        kachej.read(key, result)
    }

    override suspend fun <T: Serializable> put(key: String, value: T, result: Result<Unit>.() -> Unit) {
        kachej.write(key, value, result)
    }

    override suspend fun clear(key: String) {
        kachej.clean(key)
    }

    override suspend fun clear() {
        kachej.cleanAll()
    }
}