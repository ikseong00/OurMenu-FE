package com.example.ourmenu.data

data class ErrorResponse(
    val isSuccess: Boolean,
    val errorResponse: ErrorData
)

data class ErrorData(
    val status: Int,
    val code: String,
    val message: String,
)
