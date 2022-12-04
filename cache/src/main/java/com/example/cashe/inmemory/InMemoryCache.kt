package com.example.cashe.inmemory

open class InMemoryCache {

    internal val mutableCache = HashMap<Any, Any?>()

    val cache: Map<Any, Any?> = mutableCache

    inline operator fun <reified T : Any> get(key: Any): T? = runCatching {
        cache[key]?.takeIf { it is T }?.let { it as T }
    }.getOrNull()

    operator fun <T : Any> set(key: Any, value: T?): T? {
        mutableCache[key] = value
        return value
    }
}
