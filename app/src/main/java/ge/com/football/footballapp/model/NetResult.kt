package ge.com.football.footballapp.model

data class NetResult <T>(
    val resultCode: Int,
    val match: T
    )