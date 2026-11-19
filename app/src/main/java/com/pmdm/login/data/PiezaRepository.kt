package com.pmdm.login.data

import com.pmdm.login.data.mocks.pieza.PiezaDaoMock
import com.pmdm.login.model.Pieza



class PiezaRepository (
) {
    private var piezaDao= PiezaDaoMock()
    fun getPiezas(): List<Pieza> {
        return piezaDao.getTodos().map { it.aPieza() }
    }

    fun countPiezas(): Int {
        return piezaDao.count()
    }
}
