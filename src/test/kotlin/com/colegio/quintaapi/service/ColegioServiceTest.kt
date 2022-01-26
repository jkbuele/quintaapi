package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.repository.ColegioRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.io.File

@SpringBootTest
class ColegioServiceTest {

    @InjectMocks
    lateinit var colegioService: ColegioService

    @Mock
    lateinit var colegioRepository: ColegioRepository

    val returnObject: Colegio = Colegio().apply {
        id = 1
        nombreC = "salesianas"
        direccion = "cuenca"

    }
    val newObject: Colegio = Colegio().apply {
        id = 1
        nombreC = "salesianas"
        direccion = "cuenca"
    }

    @Test
    fun saveIsCorrect() {
        Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(returnObject)
        val response = colegioService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombreC, newObject.nombreC)
        Assertions.assertEquals(response.direccion, newObject.direccion)
    }

    val jsonString = File("./src/test/resources/colegio/crearColegio.json").readText(Charsets.UTF_8)
    val colegioMock = Gson().fromJson(jsonString, Colegio::class.java)

    @Test
    fun saveColegio() {
        Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(colegioMock)
        val response = colegioService.save(colegioMock)
        Assertions.assertEquals(response.id, colegioMock.id)
        Assertions.assertEquals(response.nombreC, colegioMock.nombreC)
        Assertions.assertEquals(response.direccion, colegioMock.direccion)
    }

    @Test
    fun saveColegioFailedNombreC() {
        Assertions.assertThrows(Exception::class.java) {
            colegioMock.apply { nombreC = "    " }
            Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(colegioMock)
            colegioService.save(colegioMock)
        }
    }


    @Test
    fun saveColegioFailedDireccion() {
        Assertions.assertThrows(Exception::class.java) {
            colegioMock.apply {
                direccion = "    "
            }
            Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(colegioMock)
            colegioService.save(colegioMock)
        }
    }

    @Test
    fun updateIsIdColegioCorrect() {
        Mockito.`when`(colegioRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(returnObject)
        val response = colegioService.save(colegioMock)
        Assertions.assertEquals(response.id, colegioMock.id)
        Assertions.assertEquals(response.nombreC, colegioMock.nombreC)
        Assertions.assertEquals(response.direccion, colegioMock.direccion)
    }

    @Test
    fun updatedIdNotExitsFailed() {

        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(colegioRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(returnObject)
            colegioService.update(colegioMock)

        }
    }

    @Test
    fun updateIsFailedNombrecIsNull() {
        Assertions.assertThrows(Exception::class.java) {
            colegioMock.apply {
                nombreC = " "
            }
            Mockito.`when`(colegioRepository.findById(colegioMock.id)).thenReturn(colegioMock)
            Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(colegioMock)
            colegioService.update(colegioMock)

        }
    }

    @Test
    fun updateIsFailedDireccionIsNull() {
        Assertions.assertThrows(Exception::class.java) {
            colegioMock.apply {
                direccion = " "
            }
            Mockito.`when`(colegioRepository.findById(colegioMock.id)).thenReturn(colegioMock)
            Mockito.`when`(colegioRepository.save(Mockito.any(Colegio::class.java))).thenReturn(colegioMock)
            colegioService.update(colegioMock)

        }
    }



}