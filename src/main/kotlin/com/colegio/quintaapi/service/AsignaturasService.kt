package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Asignaturas
import com.colegio.quintaapi.repository.AsignaturasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class AsignaturasService {

    @Autowired
    lateinit var asignaturasRepository: AsignaturasRepository


    fun list(): List<Asignaturas> {
        return asignaturasRepository.findAll()
    }

    fun save(asignaturas: Asignaturas): Asignaturas {
        return asignaturasRepository.save(asignaturas)
    }

    fun update(asignaturas: Asignaturas): Asignaturas {
        return asignaturasRepository.save(asignaturas)

    }

    fun updateDescricion (asignaturas: Asignaturas):Asignaturas {
        val response = asignaturasRepository.findById(asignaturas.id)
            ?: throw Exception()
        response.apply {
            this.descricion=asignaturas.descricion
        }
        return asignaturasRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        asignaturasRepository.deleteById(id)
        return true
    }


}