package caramelo.com.br.notepad.ui.notedetail

import android.arch.lifecycle.ViewModel
import caramelo.com.br.notepad.component
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
}