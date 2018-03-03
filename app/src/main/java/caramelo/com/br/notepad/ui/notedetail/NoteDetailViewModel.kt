package caramelo.com.br.notepad.ui.notedetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import caramelo.com.br.notepad.component
import caramelo.com.br.notepad.model.Note
import caramelo.com.br.notepad.repository.NoteRepository
import javax.inject.Inject

/**
 * Created by lucascaramelo on 02/03/18.
 */
class NoteDetailViewModel: ViewModel() {
    init {
        component.inject(this)
    }

    @Inject lateinit var noteRepository: NoteRepository

    var intent: Intent? = null
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var note: MutableLiveData<Note>? = null
        get() {
            if (field == null) {
                val note = MutableLiveData<Note>()
                note.value = Note()
                field = note
                intent?.getStringExtra(NoteDetailActivity.EXTRA_NOTE_ID)?.let {
                    findNoteById(it)
                }
            }
            return field
        }

    private fun findNoteById(id: String) {
        isLoading.postValue(true)
        noteRepository.get(id,
                onNoteGetSuccess = {
                    it?.let {
                        note?.postValue(it)
                        isLoading.postValue(false)
                    }
                },
                onNoteGetFail = {
                    isLoading.postValue(false)
                }
        )
    }

    fun save() {
        val note = this.note?.value ?: Note()
        noteRepository.save(note)
    }
}