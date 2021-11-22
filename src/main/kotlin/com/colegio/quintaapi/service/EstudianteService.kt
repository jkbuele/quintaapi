package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Estudiante
import com.colegio.quintaapi.repository.EstudianteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class EstudianteService {

    @Autowired
    lateinit var estudianteRepository: EstudianteRepository


    fun list(): List<Estudiante> {

        return estudianteRepository.findAll()
    }

    fun save(estudiante: Estudiante): Estudiante {
        return estudianteRepository.save(estudiante)
    }

    fun update(estudiante: Estudiante):Estudiante {
        return estudianteRepository.save(estudiante)
    }

    fun updateNombre (estudiante: Estudiante):Estudiante {
        val response = estudianteRepository.findById(estudiante.id)
            ?: throw Exception()
        response.apply {
            this.nombreE=estudiante.nombreE
        }
        return estudianteRepository.save(response)
    }


    fun delete (id:Long): Boolean{
        estudianteRepository.deleteById(id)
        return true
    }

}