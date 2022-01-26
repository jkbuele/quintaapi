package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Asignaturas
import com.colegio.quintaapi.repository.AsignaturasRepository
import com.colegio.quintaapi.repository.ColegioRepository
import com.colegio.quintaapi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class AsignaturasService {

    @Autowired
    lateinit var asignaturasRepository: AsignaturasRepository

    @Autowired
    lateinit var colegioRepository: ColegioRepository

    @Autowired
    lateinit var estudianteRepository: EstudianteRepository


    fun list(): List<Asignaturas> {
        return asignaturasRepository.findAll()
    }

    fun save(@RequestBody asignaturas: Asignaturas): Asignaturas {

        try {
            asignaturas.descricion?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo descricion se encuentra vacio")
            return asignaturasRepository.save(asignaturas)

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

            asignaturas.descricion ?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("campo descricion vacio")

             return asignaturasRepository.save(asignaturas)

        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no existe", ex)
        }
    }

    fun updateDescricion (asignaturas: Asignaturas):Asignaturas {
        try {
            asignaturas.descricion?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo descripcion vacío")

            val response = asignaturasRepository.findById(asignaturas.id)
                ?: throw Exception("El ID ${asignaturas.id} de asignaturas no existe")
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


    fun delete (idAsignaturas :Long): Boolean{
        asignaturasRepository.deleteById(idAsignaturas)
        return true
        /*

        try {
            asignaturasRepository.findById(idAsignaturas)
                ?: throw Exception ("idAsignaturas no existe")
            asignaturasRepository.deleteById(id!!)
            return true
        }catch (ex:Exception){
            throw Exception()
        }

        */


    }



    fun verificacionLetras(descricion:String?): Boolean{
        if(descricion?.length!! == 20){
            return false
        }
        return true
    }


}