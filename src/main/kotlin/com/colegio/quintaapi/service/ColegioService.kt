package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.repository.ColegioRepository
import com.colegio.quintaapi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ColegioService {

    @Autowired
    lateinit var colegioRepository: ColegioRepository


    fun list(): List<Colegio> {

        return colegioRepository.findAll()
    }

    fun save(colegio: Colegio): Colegio {
        //validacion

        try {
            if (colegio.nombreC.equals("") || colegio.direccion.equals("")) {
                throw Exception("completar el campo")
            } else {
                return colegioRepository.save(colegio)
            }
        }
        catch(ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }

    }

    fun update(colegio: Colegio): Colegio {
        try {
            val response = colegioRepository.findById(colegio.id)
                ?: throw Exception("El ID ${colegio.id}  no existe")

            if (colegio.nombreC.equals("") || colegio.direccion.equals("")) {
                throw Exception("completar el campo ")
            } else {
                return colegioRepository.save(colegio)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no existe", ex)
        }

    }

    fun updateDireccion(colegio: Colegio): Colegio {
        try {
            colegio.direccion?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("campo vacío")

            val response = colegioRepository.findById(colegio.id)
                ?: throw Exception("El ID ${colegio.id}  no existe")
            response.apply {
                this.nombreC = colegio.nombreC
            }
            return colegioRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


    fun delete (id:Long): Boolean{
        colegioRepository.deleteById(id)
        return true
    }
}