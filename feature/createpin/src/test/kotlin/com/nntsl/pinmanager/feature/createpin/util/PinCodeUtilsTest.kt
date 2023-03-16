package com.nntsl.pinmanager.feature.createpin.util

import org.junit.Test
import kotlin.test.assertTrue

class PinCodeUtilsTest {

    @Test
    fun testGeneratePINCode() {
        val pinCode = generatePinCode()
        assertTrue(pinCode.matches("\\d{6}".toRegex()))
        assertTrue(pinCode.toCharArray().groupBy { it }.values.all { it.size < 3 })
    }
}
