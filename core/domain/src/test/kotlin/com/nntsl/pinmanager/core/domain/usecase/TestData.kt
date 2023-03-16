package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.model.PinCode

val samplePinCodes = listOf(
    PinCode(
        id = 1,
        name = "1",
        code = "123456"
    ),
    PinCode(
        id = 2,
        name = "2",
        code = "234567"
    )
)

val pinCodeToSave = PinCode(
    id = 3,
    name = "3",
    code = "345678"
)

val pinCodeToDelete = samplePinCodes.last()
