package com.colegio.quintaapi.repository

import com.colegio.quintaapi.model.Colegio
import org.springframework.data.jpa.repository.JpaRepository

interface ColegioRepository: JpaRepository<Colegio, Long> {
    fun findById(id: Long?): Colegio?
}