package com.nntsl.pinmanager.feature.createpin.util

import kotlin.random.Random

fun generatePinCode(): String {
    val digits = mutableListOf<Int>()
    while (digits.size < 6) {
        val digit = Random.nextInt(10)
        if (digits.count { it == digit } < 3) {
            digits.add(digit)
        }
    }
    return digits.joinToString("")
}
