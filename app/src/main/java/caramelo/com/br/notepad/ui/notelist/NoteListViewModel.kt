package caramelo.com.br.notepad.ui.notelist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import caramelo.com.br.notepad.component
import caramelo.com.br.notepad.model.Note
import caramelo.com.br.notepad.repository.NoteRepository
import javax.inject.Inject


/**
 * Created by lucascaramelo on 02/03/18.
 */
class NoteListViewModel: ViewModel() {

    init {
        component.inject(this)
    }

    @Inject lateinit var noteRepository: NoteRepository

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isEmptyView: MutableLiveData<Boolean> = MutableLiveData()
    val isRecyclerView: MutableLiveData<Boolean> = MutableLiveData()
    var noteList: MutableLiveData<List<Note>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                loadNotes()
            }
            return field
        }

    fun loadNotes() {
        isLoading.postValue(true)
        isEmptyView.postValue(false)
        isRecyclerView.postValue(false)
        noteRepository.list(
                onNoteListSuccess = {
                    noteList?.value = it
                    validNoteList()
                    isLoading.postValue(false)
                },
                onNoteListFail = {
                    isEmptyView.postValue(true)
                    isLoading.postValue(false)
                }
        )
    }

    private fun validNoteList() {
        val isEmpty = noteList?.value?.isEmpty() ?: true
        isEmptyView.postValue(isEmpty)
        isRecyclerView.postValue(!isEmpty)
    }

    fun deleteDelete(position: Int) {
        val list = noteList?.value?.toMutableList()
        val note = list?.get(position)
        note?.let {
            noteRepository.delete(it.id!!)
            list.remove(it)
        }
        noteList?.value = list
        validNoteList()
    }

}