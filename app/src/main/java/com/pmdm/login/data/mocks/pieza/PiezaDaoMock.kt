package com.pmdm.login.data.mocks.pieza

import com.pmdm.login.model.CategoriaColeccion

class PiezaDaoMock {

    val urlBase = "http://10.0.2.2:8080/coleccion/"

    val piezas = listOf(
        PiezaMock(
            id = 1,
            titulo = "A Game of Thrones",
            categoria = CategoriaColeccion.LIBROS_RAROS,
            epoca = "1996",
            autor = "George R. R. Martin",
            valorEstimado = 850.0,
            notas = "Primera novela de la saga Canción de Hielo y Fuego. Primera edición estadounidense publicada por Bantam Spectra en 1996.",
            foto = "${urlBase}1_game_of_thrones.png"
        ),
        PiezaMock(
            id = 2,
            titulo = "A Clash of Kings",
            categoria = CategoriaColeccion.LIBROS_RAROS,
            epoca = "1998",
            autor = "George R. R. Martin",
            valorEstimado = 920.0,
            notas = "Segunda entrega de la saga. Primera edición publicada en 1998. Continúa la Guerra de los Cinco Reyes por el Trono de Hierro.",
            foto = "${urlBase}2_clash_of_kings.png"
        ),
        PiezaMock(
            id = 3,
            titulo = "A Storm of Swords",
            categoria = CategoriaColeccion.LIBROS_RAROS,
            epoca = "2000",
            autor = "George R. R. Martin",
            valorEstimado = 1100.0,
            notas = "Tercera novela de la saga, publicada en 2000. Destaca por sus acontecimientos políticos y las grandes traiciones de Poniente.",
            foto = "${urlBase}3_storm_of_swords.png"
        ),
        PiezaMock(
            id = 4,
            titulo = "A Feast for Crows",
            categoria = CategoriaColeccion.LIBROS_RAROS,
            epoca = "2005",
            autor = "George R. R. Martin",
            valorEstimado = 780.0,
            notas = "Cuarta entrega de la saga, publicada en 2005. Explora las consecuencias de la guerra y la lucha por el poder en los Siete Reinos.",
            foto = "${urlBase}4_feast_for_crows.png"
        ),
        PiezaMock(
            id = 5,
            titulo = "A Dance with Dragons",
            categoria = CategoriaColeccion.LIBROS_RAROS,
            epoca = "2011",
            autor = "George R. R. Martin",
            valorEstimado = 1250.0,
            notas = "Quinta novela de la saga, publicada en 2011. Presenta acontecimientos paralelos a los de Festín de Cuervos y continúa las historias de Daenerys y Jon Nieve.",
            foto = "${urlBase}5_dance_with_dragons.png"
        ),
        PiezaMock(
            id = 6,
            titulo = "Fire & Blood",
            categoria = CategoriaColeccion.LIBROS_RAROS,
            epoca = "2018",
            autor = "George R. R. Martin",
            valorEstimado = 950.0,
            notas = "Historia de la dinastía Targaryen, desde Aegon el Conquistador hasta los conflictos sucesorios de sus descendientes. Primera edición publicada en 2018.",
            foto = "${urlBase}6_fire_and_blood.png"
        )
    ).toMutableList()

    fun getTodos(): List<PiezaMock> = piezas.toList()

    fun getPorId(id: Int): PiezaMock? =
        piezas.firstOrNull { it.id == id }

    fun insert(pieza: PiezaMock) {
        piezas.removeAll { it.id == pieza.id }
        piezas.add(pieza)
    }

    fun count(): Int = piezas.size
}
