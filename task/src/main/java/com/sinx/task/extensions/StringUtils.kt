package com.sinx.task.extensions

object StringUtils {
    fun stringToWords(s: String): MutableSet<String> =
        s.trim().splitToSequence(' ')
            .filter { it.isNotEmpty() }
            .toList()
            .toMutableSet()
}