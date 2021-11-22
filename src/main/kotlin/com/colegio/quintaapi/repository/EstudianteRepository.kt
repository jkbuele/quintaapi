package com.colegio.quintaapi.repository

import com.colegio.quintaapi.model.Estudiante
import org.springframework.data.jpa.repository.JpaRepository

interface EstudianteRepository: JpaRepository<Estudiante, Long> {
    fun findById(id: Long?): Estudiante?

}