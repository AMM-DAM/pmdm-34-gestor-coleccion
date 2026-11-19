package com.pmdm.login.data.mocks.pieza

import com.pmdm.login.model.CategoriaColeccion


data class PiezaMock(
    val id: Int,
    val titulo: String,
    val categoria: CategoriaColeccion,
    val epoca: String,
    val autor: String,
    val valorEstimado: Double,
    val notas: String,
    val foto: String
)
