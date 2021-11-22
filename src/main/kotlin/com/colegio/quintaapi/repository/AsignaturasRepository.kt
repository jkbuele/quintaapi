package com.colegio.quintaapi.repository

import com.colegio.quintaapi.model.Asignaturas
import org.springframework.data.jpa.repository.JpaRepository

interface AsignaturasRepository: JpaRepository<Asignaturas, Long> {

    fun findById(id: Long?): Asignaturas?
}