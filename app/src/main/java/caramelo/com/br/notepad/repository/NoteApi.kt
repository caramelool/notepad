package caramelo.com.br.notepad.repository

import caramelo.com.br.notepad.model.Note
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by lucascaramelo on 01/03/18.
 */
interface NoteApi {

    @Headers("Content-Type: application/json")
    @GET("/nota")
    fun list(): Call<List<Note>>

    @Headers("Content-Type: application/json")
    @GET("/nota/{id}")
    fun get(@Path("id") id: String): Call<Note>

    @Headers("Content-Type: application/json")
    @POST("/nota")
    fun save(@Body nota: Note): Call<Note>

}