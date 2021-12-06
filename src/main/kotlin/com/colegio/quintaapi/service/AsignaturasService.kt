package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Asignaturas
import com.colegio.quintaapi.repository.AsignaturasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class AsignaturasService {

    @Autowired
    lateinit var asignaturasRepository: AsignaturasRepository


    fun list(): List<Asignaturas> {
        return asignaturasRepository.findAll()
    }

    fun save(asignaturas: Asignaturas): Asignaturas {

        //validacion de guaradar la materia si... esta esta con el nombre del estudiante

        try {
            if (asignaturas.descricion .equals("") ) {
                throw Exception("completar el campo")
            } else {
                return asignaturasRepository.save(asignaturas)
            }
        }
        catch(ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)

        }

    }

    fun update(asignaturas: Asignaturas): Asignaturas {
        try {
            val response = asignaturasRepository.findById(asignaturas.id)
                ?: throw Exception("El ID ${asignaturas.id}  no existe")

            if (asignaturas.descricion .equals("") ) {
                throw Exception("completar el campo")
            } else {
                return asignaturasRepository.save(asignaturas)
            }
        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no existe", ex)
        }

    }

    fun updateDescricion (asignaturas: Asignaturas):Asignaturas {
        try {
            if (asignaturas.descricion .equals("")){
                throw Exception("campo vacío")}
            val response = asignaturasRepository.findById(asignaturas.id)
                ?: throw Exception("El ID ${asignaturas.id}  no existe")
            response.apply {
                this.descricion = asignaturas.descricion
            }
            return asignaturasRepository.save(response)

        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }

    }


    fun delete (id:Long): Boolean{
        asignaturasRepository.deleteById(id)
        return true
    }


}