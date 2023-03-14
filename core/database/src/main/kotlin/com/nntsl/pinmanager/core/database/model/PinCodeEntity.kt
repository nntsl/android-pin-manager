package com.nntsl.pinmanager.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nntsl.pinmanager.core.model.PinCode

@Entity(
    tableName = "pin_codes"
)
data class PinCodeEntity(
    val name: String,
    val code: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun PinCodeEntity.asExternalModel() = PinCode(
    name = name,
    code = code
)
