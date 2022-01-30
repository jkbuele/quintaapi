package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.model.Estudiante
import com.colegio.quintaapi.repository.ColegioRepository
import com.colegio.quintaapi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class EstudianteService {

    @Autowired
    lateinit var estudianteRepository: EstudianteRepository


    @Autowired
    lateinit var colegioRepository: ColegioRepository


    fun list(): List<Estudiante> {

        return estudianteRepository.findAll()
    }

    fun save(estudiante: Estudiante): Estudiante {
        try {
            colegioRepository.findById(estudiante.colegioId)
                ?: throw Exception("id del colegio no existe")
            //val response = colegioRepository.findById(estudiante.colegioId)
               // ?: throw Exception("El ID ${estudiante.colegioId}  no existe")

            estudiante.nombreE?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Nombre Estudiante no debe ser vacio")

            estudiante.apellido?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Apellido no debe ser vacio")
            return estudianteRepository.save(estudiante)

        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


    fun update(estudiante: Estudiante): Estudiante {

        try {

            val response = estudianteRepository.findById(estudiante.id)
                ?: throw Exception("El ID ${estudiante.id}  no existe")

            estudiante.nombreE?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("campo nombreE vacio")
            estudiante.apellido?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("campo apellido vacio")

            return estudianteRepository.save(estudiante)
        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no existe", ex
            )
        }
    }

    fun updateNombre(estudiante: Estudiante): Estudiante {
        try {

            val response = estudianteRepository.findById(estudiante.id)
                ?: throw Exception("El ID ${estudiante.id} de estudiante no existe")

            estudiante.nombreE?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo nombreE esta vacío")

            response.apply {
                this.nombreE = estudiante.nombreE
            }
            return estudianteRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex
            )
        }
    }

    fun delete(id : Long?): Boolean {
        try {
            estudianteRepository.findById(id)
                ?: throw Exception("id no existe")
            estudianteRepository.deleteById(id!!)
            return true

        } catch (ex: Exception) {
            throw Exception()
        }


    }
}