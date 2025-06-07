package com.cursokotlin.ingeweek.recyclerEventos

data class Actividad(
    val dia: String,
    val fecha: String,
    val hora: String,
    val actividad: String? = null,
    val lugar: String? = null,
    val tema: String? = null,
    val ponente: String? = null,
    val categoria: String
)