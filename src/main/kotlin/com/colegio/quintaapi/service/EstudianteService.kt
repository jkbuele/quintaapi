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

    fun save(estudiante: Estudiante): Estudiante{
        //validacion

        try {

            colegioRepository.findById(estudiante.colegioId)
                ?: throw Exception("id E no existe")

            if (estudiante.nombreE.equals("") || estudiante.apellido .equals("")) {
                throw Exception("completar campo")
            } else {
                return estudianteRepository.save(estudiante)
            }

        }

        catch(ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }

    }


    fun update(estudiante: Estudiante):Estudiante {

        try {

            val response = estudianteRepository.findById(estudiante.id)
                ?: throw Exception("El ID ${estudiante.id}  no existe")

            if (estudiante.nombreE.equals("") || estudiante.apellido.equals("")) {
                throw Exception("completar el campo ")
            } else {
                return estudianteRepository.save(estudiante)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no existe", ex)
        }
    }

    fun updateNombre (estudiante: Estudiante):Estudiante {
        try {
            if (estudiante.nombreE.equals("")) {
                throw Exception("campo vacío")
            }
            val response = estudianteRepository.findById(estudiante.id)
                ?: throw Exception("El ID ${estudiante.id}  no existe")
            response.apply {
                this.nombreE = estudiante.nombreE
            }
            return estudianteRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (id:Long): Boolean{
        estudianteRepository.deleteById(id)
        return true
    }

}