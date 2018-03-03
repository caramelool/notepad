package caramelo.com.br.notepad.ext

import android.arch.lifecycle.ViewModel
import caramelo.com.br.notepad.NoteApplication

/**
 * Created by lucascaramelo on 02/03/18.
 */
val ViewModel.component by lazy { NoteApplication.noteComponent }