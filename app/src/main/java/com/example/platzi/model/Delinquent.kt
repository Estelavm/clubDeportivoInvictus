package com.example.platzi.model

data class Delinquent(
    val nombre: String,
    val apellido: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val cuotasAdeudadas: Int,
    val totalAdeudado: Double
)
