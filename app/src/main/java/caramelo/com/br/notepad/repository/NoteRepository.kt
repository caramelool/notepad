package caramelo.com.br.notepad.repository

import caramelo.com.br.notepad.model.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by lucascaramelo on 01/03/18.
 */
class NoteRepository(retrofit: Retrofit) {

    private val api by lazy { retrofit.create(NoteApi::class.java) }

    fun list(onNoteListSuccess: (List<Note>?) -> Unit,
             onNoteListFail: (Throwable?) -> Unit) {
        api.list().enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>?, response: Response<List<Note>>?) {
                onNoteListSuccess(response?.body())
            }
            override fun onFailure(call: Call<List<Note>>?, t: Throwable?) {
                onNoteListFail(t)
            }
        })
    }

    fun get(id: String,
            onNoteGetSuccess: (Note?) -> Unit,
            onNoteGetFail: (Throwable?) -> Unit) {
        api.get(id).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>?, response: Response<Note>?) {
                onNoteGetSuccess(response?.body())
            }
            override fun onFailure(call: Call<Note>?, t: Throwable?) {
                onNoteGetFail(t)
            }
        })
    }

    fun save(note: Note,
             onNoteSaveSuccess: (Note?) -> Unit = {},
             onNoteSaveFail: (Throwable?) -> Unit = {}) {
        api.save(note).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>?, response: Response<Note>?) {
                onNoteSaveSuccess(response?.body())
            }
            override fun onFailure(call: Call<Note>?, t: Throwable?) {
                onNoteSaveFail(t)
            }
        })
    }

    fun delete(id: String,
             onNoteDeleteSuccess: (Note?) -> Unit = {},
             onNoteDeleteFail: (Throwable?) -> Unit = {}) {
        api.delete(id).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>?, response: Response<Note>?) {
                onNoteDeleteSuccess(response?.body())
            }
            override fun onFailure(call: Call<Note>?, t: Throwable?) {
                onNoteDeleteFail(t)
            }
        })
    }
}