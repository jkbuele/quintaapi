package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Asignaturas
import com.colegio.quintaapi.model.Estudiante

import com.colegio.quintaapi.repository.AsignaturasRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class AsignaturasServiceTest {

    @InjectMocks
    lateinit var asignaturasService: AsignaturasService

    @Mock
    lateinit var asignaturasRepository: AsignaturasRepository

    val returnObject: Asignaturas = Asignaturas().apply {
        id = 1
        descricion = "matematicas"
    }
    val newObject: Asignaturas = Asignaturas().apply {
        id = 1
        descricion = "matematicas"
    }

    @Test
    fun saveIsCorrect() {
        Mockito.`when`(asignaturasRepository.save(Mockito.any(Asignaturas::class.java))).thenReturn(returnObject)
        val response = asignaturasService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.descricion, newObject.descricion)

    }
    val jsonString = File("./src/test/resources/asignaturas/crearAsignaturas.json").readText(Charsets.UTF_8)
    val asignaturasMock = Gson().fromJson(jsonString, Asignaturas::class.java)

    @Test
    fun saveAsignaturas() {
        Mockito.`when`(asignaturasRepository.save(Mockito.any(Asignaturas::class.java))).thenReturn(asignaturasMock)
        val response = asignaturasService.save(asignaturasMock)
        Assertions.assertEquals(response.id, asignaturasMock.id)
        Assertions.assertEquals(response.descricion, asignaturasMock.descricion)

    }
    @Test
    fun saveAsignaturasFailedDescricion(){
        Assertions.assertThrows(Exception::class.java) {
            asignaturasMock.apply { descricion ="    "}
            Mockito.`when`(asignaturasRepository.save(Mockito.any(Asignaturas::class.java))).thenReturn(asignaturasMock)
            asignaturasService.save(asignaturasMock)
        }}

    @Test
    fun updateIsIdAsignCorrect() {
        Mockito.`when`(asignaturasRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(asignaturasRepository.save(Mockito.any(Asignaturas::class.java))).thenReturn(returnObject)
        val response = asignaturasService.save(asignaturasMock)
        Assertions.assertEquals(response.id, asignaturasMock.id)
        Assertions.assertEquals(response.descricion, asignaturasMock.descricion)

    }

    @Test
    fun updatedIdAsigNotExitsFailed() {

        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(asignaturasRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(asignaturasRepository.save(Mockito.any(Asignaturas::class.java))).thenReturn(returnObject)
            asignaturasService.update(asignaturasMock)

        }
    }

    @Test
    fun updateIsFailedDescricionEIsNull() {
        Assertions.assertThrows(Exception::class.java) {
            asignaturasMock.apply {
                descricion = " "
            }
            Mockito.`when`(asignaturasRepository.findById(asignaturasMock.id)).thenReturn(asignaturasMock)
            Mockito.`when`(asignaturasRepository.save(Mockito.any(Asignaturas::class.java))).thenReturn(asignaturasMock)
            asignaturasService.update(asignaturasMock)

        }}






}