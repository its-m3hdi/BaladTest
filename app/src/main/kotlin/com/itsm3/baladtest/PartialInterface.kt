package com.itsm3.baladtest

import java.lang.reflect.Proxy.newProxyInstance

inline fun <reified T> Any.materialize(): T =
    materialize(T::class.java, this)

fun <T> materialize(i: Class<T>, x: Any = object {}): T {
    @Suppress("UNCHECKED_CAST")
    return newProxyInstance(i.classLoader, arrayOf(i)) { _, m, args ->
        x.javaClass.methods
            .asSequence()
            .filter {
                it.name == m.name
                        && it.parameterTypes!!.contentEquals(m.parameterTypes)
            }
            .map {
                it.invoke(x, *args.orEmpty())
            }.firstOrNull()
    } as T
}