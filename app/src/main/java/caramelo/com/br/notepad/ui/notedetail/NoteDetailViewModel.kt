package caramelo.com.br.notepad.ui.notedetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import caramelo.com.br.notepad.ext.component
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

    lateinit var intent: Intent
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var note: MutableLiveData<Note>? = null
        get() {
            if (field == null) {
                field = MutableLiveData<Note>()
                start()
            }
            return field
        }

    private fun start() {
        isLoading.postValue(false)
        note?.value = Note()
        intent.getStringExtra(NoteDetailActivity.EXTRA_NOTE_ID)?.let {
            findNoteById(it)
        }
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

    fun setTitle(title: String) {
        note?.value?.title = title
    }

    fun setContent(content: String) {
        note?.value?.content = content
    }

    fun save() {
        val note = this.note?.value ?: Note()
        if (note.title?.isBlank() == true
                && note.content?.isBlank() == true) {
            note.id?.let {
                noteRepository.delete(note.id)
            }
        } else {
            noteRepository.save(note)
        }
    }
}