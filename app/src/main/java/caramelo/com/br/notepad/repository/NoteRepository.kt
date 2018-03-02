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

    fun list(onListNoteSuccess: (List<Note>?) -> Unit,
             onListNoteFail: (Throwable?) -> Unit) {
        api.list().enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>?, response: Response<List<Note>>?) {
                onListNoteSuccess(response?.body())
            }
            override fun onFailure(call: Call<List<Note>>?, t: Throwable?) {
                onListNoteFail(t)
            }
        })
    }

    fun get(id: String,
            onGetNoteSuccess: (Note?) -> Unit,
            onGetNoteFail: (Throwable?) -> Unit) {
        api.get(id).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>?, response: Response<Note>?) {
                onGetNoteSuccess(response?.body())
            }
            override fun onFailure(call: Call<Note>?, t: Throwable?) {
                onGetNoteFail(t)
            }
        })
    }

    fun save(note: Note,
             onSaveNoteSuccess: (Note?) -> Unit,
             onSaveNoteFail: (Throwable?) -> Unit) {
        api.save(note).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>?, response: Response<Note>?) {
                onSaveNoteSuccess(response?.body())
            }
            override fun onFailure(call: Call<Note>?, t: Throwable?) {
                onSaveNoteFail(t)
            }
        })
    }
}