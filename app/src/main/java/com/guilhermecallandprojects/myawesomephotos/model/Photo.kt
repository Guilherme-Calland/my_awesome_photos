package com.guilhermecallandprojects.myawesomephotos.model

data class Photo(
    val id: Int,
    val image: String?,
    val date: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
)