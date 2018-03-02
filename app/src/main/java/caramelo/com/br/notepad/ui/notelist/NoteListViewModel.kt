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

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val emptyView: MutableLiveData<Boolean> = MutableLiveData()
    var noteList: MutableLiveData<List<Note>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                loadNotes()
            }
            return field
        }

    fun loadNotes() {
        loading.postValue(true)
        emptyView.postValue(false)
        noteRepository.list(
                onListNoteSuccess = {
                    if (it?.isNotEmpty() == true) {
                        noteList?.postValue(it)
                    } else {
                        emptyView.postValue(true)
                    }
                    loading.postValue(false)
                },
                onListNoteFail = {
                    emptyView.postValue(true)
                    loading.postValue(false)
                }
        )
    }

}