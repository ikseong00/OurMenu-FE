package com.example.ourmenu.data

import java.io.Serializable

// 테스트용 더미 데이터 API 사용 X
data class DummyMenuData(
    val imageUrl : String,
    val menu : String,
    val store : String,
    val address: String,
) : Serializable
