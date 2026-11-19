package com.pmdm.login.data

import com.pmdm.login.data.mocks.pieza.PiezaMock
import com.pmdm.login.data.mocks.user.UserMock

import com.pmdm.login.model.Pieza
import com.pmdm.login.model.User

fun Pieza.aPiezaMock(): PiezaMock= PiezaMock(
    id = id,
    titulo = titulo,
    categoria = categoria,
    epoca = epoca,
    autor = autor,
    valorEstimado = valorEstimado,
    notas = notas,
    foto = foto
)

fun PiezaMock.aPieza(): Pieza = Pieza(
    id = id,
    titulo = titulo,
    categoria = categoria,
    epoca = epoca,
    autor = autor,
    valorEstimado = valorEstimado,
    notas = notas,
    foto = foto
)

fun User.toUserMock(): UserMock = UserMock(
    email = email,
    username = username,
    password = password
)

fun UserMock.toUser(): User = User(
    email = email,
    username = username,
    password = password
)