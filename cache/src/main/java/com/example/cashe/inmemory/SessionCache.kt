package com.example.cashe.inmemory

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object SessionCache : InMemoryCache()

inline fun <reified T> cache(
    key: Any,
    defaultValue: T? = null,
    inMemoryCache: InMemoryCache = SessionCache
) = object : ReadWriteProperty<Any?, T?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>) =
        inMemoryCache[key] ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        inMemoryCache[key] = value
    }
}
