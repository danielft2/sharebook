package com.example.sharebook.core.utils

import com.example.sharebook.R

data class ValidationFieldResult(
    val isValid: Boolean = false,
    val resId: Int = R.string.field_valid
)
