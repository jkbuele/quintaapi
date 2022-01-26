package com.colegio.quintaapi.service


import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.model.Estudiante
import com.colegio.quintaapi.repository.EstudianteRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class EstudianteServiceTest {

    @InjectMocks
    lateinit var estudianteService: EstudianteService

    @Mock
    lateinit var estudianteRepository: EstudianteRepository

    val returnObject: Estudiante = Estudiante().apply {
        id = 1
        nombreE = "alex"
        apellido = "bueno"

    }
    val newObject: Estudiante = Estudiante().apply {
        id = 1
        nombreE = "alex"
        apellido = "bueno"
    }
    /*@Test
    fun saveIsCorrecto() {
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
        val response = estudianteService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombreE, newObject.nombreE)
        Assertions.assertEquals(response.apellido, newObject.apellido)
    }*/

    val jsonString = File("./src/test/resources/estudiante/crearEstudiante.json").readText(Charsets.UTF_8)
    val estudianteMock = Gson().fromJson(jsonString, Estudiante::class.java)

   /* @Test
    fun saveEstudiante() {
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(estudianteMock)
        val response = estudianteService.save(estudianteMock)
        Assertions.assertEquals(response.id, estudianteMock.id )
        Assertions.assertEquals(response.nombreE, estudianteMock.nombreE)
        Assertions.assertEquals(response.apellido, estudianteMock.apellido)
    }*/


    @Test
    fun saveEstudianteFailedNombreE(){
        Assertions.assertThrows(Exception::class.java) {
            estudianteMock.apply { nombreE ="    "}
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(estudianteMock)
            estudianteService.save(estudianteMock)
        }}

    @Test
    fun saveEstudianteFailedApellido(){
        Assertions.assertThrows(Exception::class.java) {
            estudianteMock.apply { apellido ="    "}
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(estudianteMock)
            estudianteService.save(estudianteMock)
        }}

    @Test
    fun updateIsIdEstudianteCorrect() {
        Mockito.`when`(estudianteRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
        val response = estudianteService.update(estudianteMock)
        Assertions.assertEquals(response.id, estudianteMock.id)
        Assertions.assertEquals(response.nombreE, estudianteMock.nombreE)
        Assertions.assertEquals(response.apellido, estudianteMock.apellido)
    }

    @Test
    fun updatedIdEstNotExitsFailed() {

        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(estudianteRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(returnObject)
            estudianteService.update(estudianteMock)

        }
    }

    @Test
    fun updateIsFailedNombreEIsNull() {
        Assertions.assertThrows(Exception::class.java) {
            estudianteMock.apply {
                nombreE = " "
            }
            Mockito.`when`(estudianteRepository.findById(estudianteMock.id)).thenReturn(estudianteMock)
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(estudianteMock)
            estudianteService.update(estudianteMock)

        }}

    @Test
    fun updateIsFailedAppelidoIsNull() {
        Assertions.assertThrows(Exception::class.java) {
            estudianteMock.apply {
                apellido = " "
            }
            Mockito.`when`(estudianteRepository.findById(estudianteMock.id)).thenReturn(estudianteMock)
            Mockito.`when`(estudianteRepository.save(Mockito.any(Estudiante::class.java))).thenReturn(estudianteMock)
            estudianteService.update(estudianteMock)

        }}
}