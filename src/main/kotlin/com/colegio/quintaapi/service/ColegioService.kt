package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.repository.ColegioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ColegioService {

    @Autowired
    lateinit var colegioRepository: ColegioRepository

    fun list(): List<Colegio> {

        return colegioRepository.findAll()
    }

    fun save(colegio: Colegio): Colegio {
        return colegioRepository.save(colegio)
    }

    fun update(colegio: Colegio): Colegio {
        return colegioRepository.save(colegio)

    }

    fun updateDireccion(colegio: Colegio): Colegio {
        val response = colegioRepository.findById(colegio.id)
            ?: throw Exception()
        response.apply {
            this.direccion = colegio.direccion
        }
        return colegioRepository.save(response)
    }


    fun delete (id:Long): Boolean{
        colegioRepository.deleteById(id)
        return true
    }
}