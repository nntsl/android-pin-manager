package com.nntsl.pinmanager.feature.pincodes

import com.nntsl.pinmanager.core.model.PinCode

val testInputPinCodes = listOf(
    PinCode(
        id = 1,
        name = "1",
        code = "123456"
    ),
    PinCode(
        id = 2,
        name = "2",
        code = "234567"
    ),
    PinCode(
        id = 3,
        name = "3",
        code = "345678"
    )
)

val testInputNewPinCode = PinCode(
    id = 4,
    name = "4",
    code = "456789"
)
