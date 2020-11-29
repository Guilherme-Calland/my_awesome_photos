package com.guilhermecallandprojects.myawesomephotos.model

import java.io.Serializable

data class AwesomePhoto(
    val id: Int,
    val image: String?,
    val date: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
): Serializable