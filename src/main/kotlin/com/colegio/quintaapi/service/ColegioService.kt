package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.repository.ColegioRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class ColegioService {

    @Autowired
    lateinit var colegioRepository: ColegioRepository


    fun list(): List<Colegio> {

        return colegioRepository.findAll()
    }

    fun save(colegio: Colegio): Colegio {
        try {
            colegio.nombreC?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("NombreC no debe ser vacio")
            colegio.direccion?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Direccion no debe ser vacio")
                return colegioRepository.save(colegio)
        }
        catch(ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(colegio: Colegio): Colegio {
        try {
            colegio.nombreC?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("campo nombre vacio")
            colegio.direccion?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("campo direccion vacio")

            val response = colegioRepository.findById(colegio.id)
                ?: throw Exception("El ID ${colegio.id}  no existe")

            return colegioRepository.save(colegio)

        }
        catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "ID no existe", ex)
        }

    }

    fun updateDireccion(colegio: Colegio): Colegio {
        try {
            colegio.direccion?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("campo direccion vacio")

            val response = colegioRepository.findById(colegio.id)
                ?: throw Exception("El ID ${colegio.id} del colegio no existe")
            response.apply {
                this.direccion = colegio.direccion
            }
            return colegioRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }


    fun delete (id :Long?): Boolean{
        try{
            colegioRepository.findById(id)
                ?: throw Exception ("id no existe")
            colegioRepository.deleteById(id!!)
            return true

        }catch (ex:Exception){
            throw Exception()
        }

    }

    fun updateNombreC(colegio: Colegio) : Colegio{
        try {
            colegio.nombreC?.trim()?.isEmpty()
                ?: throw Exception("El campo se encuentra vacío")


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
        }}

    val listaColegios= listOf<String>("salesianas","benigno")

    fun validarnombrec(nombreC: String):Boolean{
        for (i in listaColegios){
            if (nombreC == i){
                return true
            }
        }
        return false

    }








}